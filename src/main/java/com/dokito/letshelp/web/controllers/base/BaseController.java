package com.dokito.letshelp.web.controllers.base;

import com.dokito.letshelp.service.models.LoginUserServiceModel;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public class BaseController {

    protected boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    protected String getUsername(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getUsername();
    }

    public ModelAndView view(String viewName, ModelAndView modelAndView) {
        modelAndView.setViewName(viewName);

        return modelAndView;
    }

    public ModelAndView view(String viewName) {
        return this.view(viewName, new ModelAndView());
    }

    public ModelAndView redirect(String url) {
        return this.view("redirect:" + url);
    }
}
