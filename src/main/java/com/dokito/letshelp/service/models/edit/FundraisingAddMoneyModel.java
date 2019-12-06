package com.dokito.letshelp.service.models.edit;

import com.dokito.letshelp.service.models.BaseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FundraisingAddMoneyModel extends BaseServiceModel {

    private String name;
    private String addMoney;
}
