package com.dokito.letshelp.service.services;

import com.dokito.letshelp.base.TestBase;
import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.data.repositories.UserRepository;
import com.dokito.letshelp.errors.UserAlreadyParticipateInEvent;
import com.dokito.letshelp.errors.UserNotFound;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends TestBase {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;
    ModelMapper modelMapper;

    @Test
    void getUserByID_whenUserDoesNotExist_shouldReturnException() {
        String id = "nqma takova id";

        Mockito.when(userRepository.getById(id));

        assertThrows(Exception.class,
                () -> userService.getUserById(id));
    }

    @Test()
    void addEventParticipating() {
        CharityEvent charityEvent = new CharityEvent();
        String id = charityEvent.getId();
        User user = new User();

        assertThrows(UserAlreadyParticipateInEvent.class,
                () -> userService.addEventParticipating(id, charityEvent, user));
    }

    @Test
    void deleteUser() {
    }

    @Test
    void makeAdmin() {
    }

    @Test
    void makeUser() {
    }
}