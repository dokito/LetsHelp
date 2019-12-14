package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.base.TestBase;
import com.dokito.letshelp.data.repositories.FundraisingRepository;
import com.dokito.letshelp.errors.FundrasingNotFound;
import com.dokito.letshelp.service.services.FundraisingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class FundraisingServiceImplTest extends TestBase {

    @MockBean
    FundraisingRepository fundraisingRepository;

    @Autowired
    FundraisingService fundraisingService;
    ModelMapper modelMapper;

    @Test
    void getById_shouldReturnExceptionIfNoCharityEventFound() {
        String id = "nqma takova id";

        Mockito.when(fundraisingRepository.findById(id));

        assertThrows(Exception.class,
                () -> fundraisingService.getById(id));
    }

    @Test
    void addContribution() {
    }

    @Test
    void getAll() {
    }
}