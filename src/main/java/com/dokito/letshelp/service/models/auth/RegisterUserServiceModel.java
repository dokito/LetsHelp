package com.dokito.letshelp.service.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterUserServiceModel {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;
}
