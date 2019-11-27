package com.dokito.letshelp.service.models.auth;

import com.dokito.letshelp.data.models.Role;
import com.dokito.letshelp.service.models.RoleServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
    private Set<RoleServiceModel> authorities;
}
