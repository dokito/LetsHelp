package com.dokito.letshelp.service.models.create;

import com.dokito.letshelp.service.models.BaseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CharityEventCreateServiceModel extends BaseServiceModel {

    private String name;
    private String description;
    private String startDate;
}
