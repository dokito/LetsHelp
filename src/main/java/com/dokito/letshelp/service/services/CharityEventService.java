package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.service.models.CharityEventServiceModel;
import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.create.CharityEventCreateModelWithCause;
import com.dokito.letshelp.service.models.create.CharityEventCreateModelWithPersonInNeed;
import com.dokito.letshelp.service.models.create.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.models.edit.CharityEventEditServiceModel;
import com.dokito.letshelp.service.models.view.CharityEventViewDetailsModel;
import com.dokito.letshelp.service.models.view.UserViewModel;

import java.util.List;

public interface CharityEventService {

    CharityEvent create(CharityEventCreateModelWithCause modelWithCause, CharityEventCreateModelWithPersonInNeed modelWithPersonInNeed);

    List<CharityEvent> getAllCharityEvents ();

    CharityEventServiceModel getCharityEventById(String id);

    CharityEventEditServiceModel editCharityEvent(String id, CharityEventEditServiceModel model);

    List<UserViewModel> getAllUsers();

    CharityEventEditServiceModel addParticipant(String id, CharityEventEditServiceModel model, UserViewModel user);

    User getUserById(String id);

    User getUserByUsername(String username);

    boolean hasTheDatePassed(String endDate);
}
