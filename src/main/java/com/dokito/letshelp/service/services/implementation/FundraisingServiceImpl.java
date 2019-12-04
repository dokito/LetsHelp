package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.Fundraising;
import com.dokito.letshelp.data.repositories.FundraisingRepository;
import com.dokito.letshelp.service.models.create.FundraisingCreateServiceModel;
import com.dokito.letshelp.service.services.FundraisingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        Fundraising fundraising = mapper.map(model, Fundraising.class);
        fundraising.setCurrentSum(BigDecimal.ZERO);
        fundraising.setStartDate(LocalDateTime.parse(model.getStartDate(), formatter));
        fundraising.setEndDate(LocalDateTime.parse(model.getEndDate(), formatter));
        repository.save(fundraising);
        return fundraising;
    }
}
