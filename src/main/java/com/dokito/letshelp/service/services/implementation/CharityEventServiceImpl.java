package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.repositories.CharityEventRepository;
import com.dokito.letshelp.service.models.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.services.CharityEventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CharityEventServiceImpl implements CharityEventService {

    private final CharityEventRepository repository;
    private final ModelMapper mapper;

    public CharityEventServiceImpl(CharityEventRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CharityEvent create(CharityEventCreateServiceModel model) {
        CharityEvent charityEvent = mapper.map(model, CharityEvent.class);
        repository.save(charityEvent);
        return charityEvent;
    }
}
