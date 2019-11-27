package com.dokito.letshelp.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class FundraisingCreateServiceModel extends BaseServiceModel{

    private String name;
    private BigDecimal requiredSum;
}
