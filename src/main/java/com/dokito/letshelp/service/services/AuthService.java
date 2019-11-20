package com.dokito.letshelp.service.services;

import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;

public interface AuthService {

    void register(RegisterUserServiceModel model);

    LoginUserServiceModel login(RegisterUserServiceModel model) throws Exception;
}
