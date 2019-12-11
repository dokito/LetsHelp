package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.data.repositories.PersonInNeedRepository;
import com.dokito.letshelp.errors.Constants;
import com.dokito.letshelp.errors.PersonInNeedNotFound;
import com.dokito.letshelp.service.models.PersonInNeedCreateServiceModel;
import com.dokito.letshelp.service.services.PersonInNeedService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<PersonInNeed> getAll() {
        return this.personInNeedRepository.findAll();
    }

    @Override
    public PersonInNeed findById(String id) {
        return this.personInNeedRepository.findById(id)
                .orElseThrow(() -> new PersonInNeedNotFound(Constants.PIN_ID_NOT_FOUND));
    }
}
