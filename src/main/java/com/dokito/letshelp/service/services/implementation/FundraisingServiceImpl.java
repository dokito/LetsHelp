package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.Fundraising;
import com.dokito.letshelp.data.repositories.FundraisingRepository;
import com.dokito.letshelp.service.models.create.FundraisingCreateServiceModel;
import com.dokito.letshelp.service.services.FundraisingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FundraisingServiceImpl implements FundraisingService {

    private final FundraisingRepository repository;
    private final ModelMapper mapper;

    public FundraisingServiceImpl(FundraisingRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Fundraising create(FundraisingCreateServiceModel model) {
        Fundraising fundraising = mapper.map(model, Fundraising.class);
        fundraising.setCurrentSum(BigDecimal.ZERO);
        repository.save(fundraising);
        return fundraising;
    }
}
