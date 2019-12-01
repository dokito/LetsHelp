package com.dokito.letshelp.service.models;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.data.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CharityEventServiceModel extends BaseServiceModel {

    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private PersonInNeed personInNeed;
    private Cause cause;
    private User responsiblePerson;
    private List<User> participantsInEvent;
}
