package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.auth.RegisterUserServiceModel;
import com.dokito.letshelp.service.services.AuthService;
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
public class AuthController extends BaseController {

    private final AuthService authService;
    private final ModelMapper mapper;

    public AuthController(AuthService authService, ModelMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm() {
        return super.view("auth/login.html");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterForm() {
        return super.view("auth/register.html");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute RegisterUserModel model) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        authService.register(serviceModel);
//        request.getSession().setAttribute("user",serviceModel.getUsername());
        return super.redirect("auth/login.html");
    }


    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute RegisterUserModel model, HttpSession session) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        try {
            LoginUserServiceModel loginUserServiceModel = authService.login(serviceModel);
            session.setAttribute("user", loginUserServiceModel);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", mapper.map(model, User.class));
            return super.redirect(".../home.html");
        } catch (Exception ex) {

            return super.redirect("auth/login.html");
        }
    }
}
