package com.dokito.letshelp.service.services;

import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;

public interface AuthValidationService {

    boolean isValid(RegisterUserServiceModel user);
}
