package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.service.models.PersonInNeedCreateServiceModel;

import java.util.List;

public interface PersonInNeedService {

    PersonInNeed register(PersonInNeedCreateServiceModel serviceModel);

    List<PersonInNeed> getAll();

    PersonInNeed findById(String id);
}
