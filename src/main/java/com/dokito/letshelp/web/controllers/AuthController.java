package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import com.dokito.letshelp.service.services.AuthService;
import com.dokito.letshelp.web.models.RegisterUserModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;
    private final ModelMapper mapper;

    public AuthController(AuthService authService, ModelMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "auth/login.html";
    }

    @GetMapping("/register")
    public String getRegisterForm() {
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserModel model) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        authService.register(serviceModel);
        return "redirect:/";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute RegisterUserModel model, HttpSession session) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        try {
            LoginUserServiceModel loginUserServiceModel = authService.login(serviceModel);
            session.setAttribute("user", loginUserServiceModel);
            return "redirect:/";
        } catch (Exception ex) {
            return "redirect:/users/login";
        }
    }
}
