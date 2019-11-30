package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.service.models.create.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.models.view.CharityEventViewDetailsModel;

import java.util.List;

public interface CharityEventService {

    CharityEvent create(CharityEventCreateServiceModel model);

    List<CharityEventCreateServiceModel> getAllCharityEvents ();

    CharityEventViewDetailsModel getCharityEventById(String id);
}
