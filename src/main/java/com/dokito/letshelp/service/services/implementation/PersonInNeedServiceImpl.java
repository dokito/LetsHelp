package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.data.repositories.PersonInNeedRepository;
import com.dokito.letshelp.service.models.PersonInNeedCreateServiceModel;
import com.dokito.letshelp.service.services.PersonInNeedService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonInNeedServiceImpl implements PersonInNeedService {

    private final PersonInNeedRepository personInNeedRepository;
    private final ModelMapper mapper;

    public PersonInNeedServiceImpl(PersonInNeedRepository personInNeedRepository, ModelMapper mapper) {
        this.personInNeedRepository = personInNeedRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonInNeed register(PersonInNeedCreateServiceModel serviceModel) {
        PersonInNeed person = mapper.map(serviceModel, PersonInNeed.class);
        personInNeedRepository.save(person);
        return person;
    }
}
