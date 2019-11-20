package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.repositories.UserRepository;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import com.dokito.letshelp.service.services.AuthValidationService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    private final UserRepository userRepository;

    public AuthValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(RegisterUserServiceModel user) {
        return this.isEmailValid(user.getEmail()) &&
                this.arePasswordsValid(user.getPassword(), user.getConfirmPassword()) &&
                this.isUsernameFree(user.getUsername());
    }

    private boolean isUsernameFree(String username) {
        return !userRepository.existsByUsername(username);
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`\\{|\\}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }
}
