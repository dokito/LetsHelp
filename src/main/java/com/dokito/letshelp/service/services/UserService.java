package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.UserServiceModel;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void register(RegisterUserServiceModel model);

    User getUserById(String id);

    void addEventParticipating(String id, CharityEvent charityEvent, User user);

    void deleteUser(String id);

    void makeAdmin(String id);

    void makeUser(String id);

    void makeContributor(String id);

    List<UserServiceModel> findAllUsers();

    UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword);
}
