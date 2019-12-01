package com.dokito.letshelp.service.models.edit;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.service.models.BaseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CharityEventEditServiceModel extends BaseServiceModel {

    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private PersonInNeed personInNeed;
    private Cause cause;
    private User responsiblePerson;
    private List<User> participantsInEvent;
}
