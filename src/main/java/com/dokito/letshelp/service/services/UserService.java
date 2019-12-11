package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;

public interface UserService {

    void register(RegisterUserServiceModel model);

    LoginUserServiceModel login(RegisterUserServiceModel model) throws Exception;

    User getUserById(String id);

    void addEventParticipating(String id, CharityEvent charityEvent, User user);
}
