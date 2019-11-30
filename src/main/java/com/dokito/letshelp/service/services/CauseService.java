package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.service.models.create.CauseCreateServiceModel;

public interface CauseService {

    Cause create(CauseCreateServiceModel serviceModel);
}
