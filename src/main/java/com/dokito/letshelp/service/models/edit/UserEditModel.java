package com.dokito.letshelp.service.models.edit;

import com.dokito.letshelp.service.models.BaseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserEditModel extends BaseServiceModel {

    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;
    private String email;
}
