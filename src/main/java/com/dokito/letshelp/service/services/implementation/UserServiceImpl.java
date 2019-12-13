package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.data.repositories.CharityEventRepository;
import com.dokito.letshelp.data.repositories.UserRepository;
import com.dokito.letshelp.errors.CharityEventNotFound;
import com.dokito.letshelp.errors.Constants;
import com.dokito.letshelp.errors.UserNotFound;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import com.dokito.letshelp.service.services.RoleService;
import com.dokito.letshelp.service.services.UserService;
import com.dokito.letshelp.service.services.HashingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final CharityEventRepository charityEventRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper mapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService,
                           CharityEventRepository charityEventRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
        this.charityEventRepository = charityEventRepository;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        this.roleService.seedRolesInDb();
        if (this.userRepository.count() == 0) {
            model.setAuthorities(this.roleService.findAllRoles());
        } else {
            model.setAuthorities(new LinkedHashSet<>());

            model.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        User user = mapper.map(model, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(Constants.USER_ID_NOT_FOUND));
    }

    @Override
    public void addEventParticipating(String id, CharityEvent charityEvent, User user) {
        CharityEvent charityEvent1 = this.charityEventRepository.findById(id)
                .orElseThrow(() -> new CharityEventNotFound(Constants.CHARITY_EVENT_ID_NOT_FOUND));

        user.getEventsParticipating().add(charityEvent1);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public void makeAdmin(String id) {

    }

    @Override
    public void makeUser(String id) {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s);
    }
}
