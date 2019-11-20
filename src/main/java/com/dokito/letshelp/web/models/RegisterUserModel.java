package com.dokito.letshelp.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterUserModel {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;
}
