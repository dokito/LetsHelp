package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.service.models.CharityEventServiceModel;
import com.dokito.letshelp.service.models.create.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.models.edit.CharityEventEditServiceModel;
import com.dokito.letshelp.service.models.view.CharityEventViewDetailsModel;
import com.dokito.letshelp.service.models.view.UserViewModel;

import java.util.List;

public interface CharityEventService {

    CharityEvent create(CharityEventCreateServiceModel model);

    List<CharityEventCreateServiceModel> getAllCharityEvents ();

    CharityEventServiceModel getCharityEventById(String id);

    CharityEventEditServiceModel editCharityEvent(String id, CharityEventEditServiceModel model);

    List<UserViewModel> getAllUsers();
}
