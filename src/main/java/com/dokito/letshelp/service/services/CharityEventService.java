package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.service.models.CharityEventCreateServiceModel;

public interface CharityEventService {

    CharityEvent create(CharityEventCreateServiceModel model);
}
