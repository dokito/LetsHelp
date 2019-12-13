package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import com.dokito.letshelp.service.services.HashingService;
import com.dokito.letshelp.service.services.UserService;
import com.dokito.letshelp.web.annotations.PageTitle;
import com.dokito.letshelp.web.controllers.base.BaseController;
import com.dokito.letshelp.web.models.RegisterUserModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper mapper;
    private final HashingService hashingService;

    public UserController(UserService userService, ModelMapper mapper, HashingService hashingService) {
        this.userService = userService;
        this.mapper = mapper;
        this.hashingService = hashingService;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Registration page")
    public ModelAndView register() {
        return super.view("user/register.html");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute RegisterUserModel model) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        userService.register(serviceModel);
        return super.redirect("/user/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Login page")
    public ModelAndView login() {
        return super.view("user/login");
    }

}
