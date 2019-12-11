package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.data.repositories.CauseRepository;
import com.dokito.letshelp.errors.CauseNotFound;
import com.dokito.letshelp.errors.Constants;
import com.dokito.letshelp.service.models.create.CauseCreateServiceModel;
import com.dokito.letshelp.service.services.CauseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Cause> getAll() {
        return this.causeRepository.findAll();
    }

    @Override
    public Cause getById(String id) {
        return this.causeRepository.findById(id)
                .orElseThrow(() -> new CauseNotFound(Constants.CAUSE_ID_NOT_FOUND));
    }
}
