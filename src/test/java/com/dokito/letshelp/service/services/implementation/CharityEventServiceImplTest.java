package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.base.TestBase;
import com.dokito.letshelp.data.repositories.CharityEventRepository;
import com.dokito.letshelp.service.services.CharityEventService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class CharityEventServiceImplTest extends TestBase {

    @MockBean
    CharityEventRepository charityEventRepository;

    @Autowired
    CharityEventService charityEventService;
    ModelMapper modelMapper;

    @Test
    void getCharityEventById() {
        String id = "nqma takova id";

        Mockito.when(charityEventRepository.findById(id));

        assertThrows(Exception.class,
                () -> charityEventService.getCharityEventById(id));
    }
}