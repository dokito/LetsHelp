package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import com.dokito.letshelp.service.services.UserService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import com.dokito.letshelp.web.models.RegisterUserModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm() {
        return super.view("users/login.html");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterForm() {
        return super.view("users/register.html");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute RegisterUserModel model) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        userService.register(serviceModel);
        return super.redirect("/users/login");
    }


    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute RegisterUserModel model, HttpSession session) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        try {
            LoginUserServiceModel loginUserServiceModel = userService.login(serviceModel);
            session.setAttribute("user", loginUserServiceModel);
            return super.redirect("/");
        } catch (Exception ex) {

            return super.redirect("/users/login");
        }
    }
}
