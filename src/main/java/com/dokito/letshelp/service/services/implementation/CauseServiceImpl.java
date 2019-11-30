package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.data.repositories.CauseRepository;
import com.dokito.letshelp.service.models.create.CauseCreateServiceModel;
import com.dokito.letshelp.service.services.CauseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CauseServiceImpl implements CauseService {

    private final CauseRepository causeRepository;
    private final ModelMapper mapper;

    public CauseServiceImpl(CauseRepository causeRepository, ModelMapper mapper) {
        this.causeRepository = causeRepository;
        this.mapper = mapper;
    }

    @Override
    public Cause create(CauseCreateServiceModel serviceModel) {
        Cause cause = mapper.map(serviceModel, Cause.class);
        causeRepository.save(cause);
        return cause;
    }
}
