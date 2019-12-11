package com.dokito.letshelp.service.models.create;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.service.models.BaseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharityEventCreateModelWithPersonInNeed extends BaseServiceModel {

    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private User responsiblePerson;
    private PersonInNeed personInNeed;
}
