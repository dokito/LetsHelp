package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.data.repositories.UserRepository;
import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import com.dokito.letshelp.service.services.RoleService;
import com.dokito.letshelp.service.services.UserService;
import com.dokito.letshelp.service.services.AuthValidationService;
import com.dokito.letshelp.service.services.HashingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class UserServiceImpl implements UserService {

    private final AuthValidationService authValidationService;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final HashingService hashingService;
    private final RoleService roleService;

    public UserServiceImpl(AuthValidationService authValidationService,
                           UserRepository userRepository,
                           ModelMapper mapper,
                           HashingService hashingService, RoleService roleService) {
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.hashingService = hashingService;
        this.roleService = roleService;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        this.roleService.seedRolesInDb();
        if (!authValidationService.isValid(model)) {
            //TODO
            return;
        }
        if (this.userRepository.count() == 0) {
            model.setAuthorities(this.roleService.findAllRoles());
        } else {
            model.setAuthorities(new LinkedHashSet<>());

            model.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        User user = mapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel model) throws Exception {

        String hashPassword = hashingService.hash(model.getPassword());
        return userRepository.findByUsernameAndPassword(model.getUsername(), hashPassword)
                .map(user -> mapper.map(user, LoginUserServiceModel.class))
                .orElseThrow(() -> new Exception("Invalid user"));
    }
}
