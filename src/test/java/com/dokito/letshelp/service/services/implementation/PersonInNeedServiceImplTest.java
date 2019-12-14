package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.base.TestBase;
import com.dokito.letshelp.data.repositories.CharityEventRepository;
import com.dokito.letshelp.data.repositories.PersonInNeedRepository;
import com.dokito.letshelp.service.services.CharityEventService;
import com.dokito.letshelp.service.services.PersonInNeedService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class PersonInNeedServiceImplTest extends TestBase {
    @MockBean
    PersonInNeedRepository personInNeedRepository;

    @Autowired
    PersonInNeedService personInNeedService;
    ModelMapper modelMapper;

    @Test
    void findById() {
        String id = "nqma takova id";

        Mockito.when(personInNeedRepository.findById(id));

        assertThrows(Exception.class,
                () -> personInNeedService.findById(id));
    }
}