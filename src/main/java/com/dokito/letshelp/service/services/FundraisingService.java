package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.Fundraising;
import com.dokito.letshelp.service.models.FundraisingCreateServiceModel;

public interface FundraisingService {

    Fundraising create(FundraisingCreateServiceModel model);
}
