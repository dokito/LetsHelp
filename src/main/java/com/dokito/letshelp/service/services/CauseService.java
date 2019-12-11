package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.service.models.create.CauseCreateServiceModel;

import java.util.List;

public interface CauseService {

    Cause create(CauseCreateServiceModel serviceModel);

    List<Cause> getAll();

    Cause getById(String id);
}
