package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.models.Role;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.data.repositories.CharityEventRepository;
import com.dokito.letshelp.data.repositories.UserRepository;
import com.dokito.letshelp.errors.CharityEventNotFound;
import com.dokito.letshelp.errors.Constants;
import com.dokito.letshelp.errors.UserAlreadyParticipateInEvent;
import com.dokito.letshelp.errors.UserNotFound;
import com.dokito.letshelp.service.models.UserServiceModel;
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

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

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

        if (user.getEventsParticipating().contains(charityEvent1)){
            throw new UserAlreadyParticipateInEvent(Constants.USER_ALREADY_PARTICIPATE_IN_EVENT);
        }
        user.getEventsParticipating().add(charityEvent1);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFound("User with given id was not found!"));

        this.userRepository.delete(user);
    }

    @Override
    public void makeAdmin(String id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USER_ID_NOT_FOUND));
        user.getAuthorities().clear();

        user.getAuthorities().add(mapper.map(this.roleService.findByAuthority("ROLE_USER"), Role.class));
        user.getAuthorities().add(mapper.map(this.roleService.findByAuthority("ROLE_ADMIN"), Role.class));
        user.getAuthorities().add(mapper.map(this.roleService.findByAuthority("ROLE_CONTRIBUTOR"), Role.class));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void makeUser(String id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USER_ID_NOT_FOUND));

        user.getAuthorities().clear();

        user.getAuthorities().add(mapper.map(this.roleService.findByAuthority("ROLE_USER"), Role.class));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void makeContributor(String id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USER_ID_NOT_FOUND));

        user.getAuthorities().clear();

        user.getAuthorities().add(mapper.map(this.roleService.findByAuthority("ROLE_USER"), Role.class));
        user.getAuthorities().add(mapper.map(this.roleService.findByAuthority("ROLE_CONTRIBUTOR"), Role.class));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.mapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername());

        if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException(Constants.PASSWORD_IS_INCORRECT);
        }

        user.setPassword(!"".equals(userServiceModel.getPassword()) ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());
        user.setEmail(userServiceModel.getEmail());

        return this.mapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s);
    }
}
