package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.web.controllers.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController extends BaseController {
    @GetMapping("/")
    public ModelAndView getIndex() {
        return super.view("home/index");
    }

    @GetMapping("/home")
    public ModelAndView getHome(HttpSession session) {
        session.getAttribute("user");
        return super.view("home/home");
    }
}
