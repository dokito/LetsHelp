package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.data.repositories.UserRepository;
import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import com.dokito.letshelp.service.services.AuthService;
import com.dokito.letshelp.service.services.AuthValidationService;
import com.dokito.letshelp.service.services.HashingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthValidationService authValidationService;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final HashingService hashingService;

    public AuthServiceImpl(AuthValidationService authValidationService,
                           UserRepository userRepository,
                           ModelMapper mapper,
                           HashingService hashingService) {
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        if (!authValidationService.isValid(model)) {
            //TODO
            return;
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
