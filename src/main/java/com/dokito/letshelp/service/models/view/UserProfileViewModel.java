package com.dokito.letshelp.service.models.view;

import com.dokito.letshelp.service.models.BaseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileViewModel extends BaseServiceModel {

    private String username;
    private String email;
}
