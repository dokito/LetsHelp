package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.Fundraising;
import com.dokito.letshelp.service.models.create.FundraisingCreateServiceModel;
import com.dokito.letshelp.service.models.create.FundraisingWithCause;
import com.dokito.letshelp.service.models.create.FundraisingWithPersonInNeed;

import java.util.List;

public interface FundraisingService {

    Fundraising create(FundraisingWithCause withCause, FundraisingWithPersonInNeed withPersonInNeed);

    Fundraising getById(String id);

    Fundraising addContribution(String id, String moneyToAdd);

    List<Fundraising> getAll();
}
