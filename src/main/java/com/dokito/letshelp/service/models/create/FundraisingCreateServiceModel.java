package com.dokito.letshelp.service.models.create;

import com.dokito.letshelp.service.models.BaseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class FundraisingCreateServiceModel extends BaseServiceModel {

    private String name;
    private BigDecimal requiredSum;
    private String startDate;
    private String endDate;
}
