package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.service.models.FundraisingCreateServiceModel;
import com.dokito.letshelp.service.services.FundraisingService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fundraisings")
public class FundraisingController extends BaseController {

    private final FundraisingService fundraisingService;
    private final ModelMapper mapper;

    public FundraisingController(FundraisingService fundraisingService, ModelMapper mapper) {
        this.fundraisingService = fundraisingService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public ModelAndView getCreateFundraisingForm(){
        return super.view("fundraisings/create_fundraising.html");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute FundraisingCreateServiceModel model){
        fundraisingService.create(model);
        return super.redirect("/");
    }
}
