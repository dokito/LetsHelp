package com.dokito.letshelp.service.models.view;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.service.models.BaseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserViewModel extends BaseServiceModel {

    private String username;
    private String firstName;
    private String lastName;
    private List<CharityEvent> eventsResponsible = new ArrayList<>();
    private List<CharityEvent> eventsParticipating = new ArrayList<>();
}
