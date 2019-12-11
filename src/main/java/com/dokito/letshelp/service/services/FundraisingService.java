package com.dokito.letshelp.service.services;

import com.dokito.letshelp.data.models.Fundraising;
import com.dokito.letshelp.service.models.create.FundraisingCreateServiceModel;

import java.util.List;

public interface FundraisingService {

    Fundraising create(FundraisingCreateServiceModel model);

    Fundraising getById(String id);

    Fundraising addContribution(String id, String moneyToAdd);

    List<Fundraising> getAll();
}
