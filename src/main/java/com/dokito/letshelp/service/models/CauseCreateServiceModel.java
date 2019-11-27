package com.dokito.letshelp.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CauseCreateServiceModel extends BaseServiceModel {

    private String name;
    private String requirements;
}
