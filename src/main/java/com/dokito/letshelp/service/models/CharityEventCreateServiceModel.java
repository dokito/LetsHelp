package com.dokito.letshelp.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharityEventCreateServiceModel extends BaseServiceModel{

    private String name;
    private String description;
}
