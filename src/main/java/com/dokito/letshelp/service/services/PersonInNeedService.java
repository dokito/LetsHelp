package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.service.models.PersonInNeedCreateServiceModel;

public interface PersonInNeedService {

    PersonInNeed create(PersonInNeedCreateServiceModel serviceModel);
}
