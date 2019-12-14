package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.base.TestBase;
import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.data.repositories.CauseRepository;
import com.dokito.letshelp.data.repositories.PersonInNeedRepository;
import com.dokito.letshelp.service.services.CauseService;
import com.dokito.letshelp.service.services.PersonInNeedService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class CauseServiceImplTest extends TestBase {

    @MockBean
    CauseRepository causeRepository;

    @Autowired
    CauseService causeService;
    ModelMapper modelMapper;

    @Test
    void getById_shouldThrowException() {
        String id = "nqma takova id";

        Mockito.when(causeRepository.findById(id));

        assertThrows(Exception.class,
                () -> causeService.getById(id));
    }

    @Test
    void getAll_shouldReturnAllCauses() {
        int size = causeRepository.findAll().size();

        Assert.assertEquals(size, 0);
    }
}