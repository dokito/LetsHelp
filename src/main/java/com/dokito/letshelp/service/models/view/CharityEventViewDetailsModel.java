package com.dokito.letshelp.service.models.view;

import com.dokito.letshelp.service.models.BaseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharityEventViewDetailsModel extends BaseServiceModel {

    private String name;
    private String description;
    private String startDate;
    private String endDate;
}
