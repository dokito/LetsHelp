package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.service.models.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.services.CharityEventService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/charityEvents")
public class CharityEventController extends BaseController {

    private final CharityEventService charityEventService;
    private final ModelMapper mapper;

    public CharityEventController(CharityEventService charityEventService, ModelMapper mapper) {
        this.charityEventService = charityEventService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public ModelAndView getCreateCharityEventForm(){
        return super.view("charityEvents/create_charity_event.html");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute CharityEventCreateServiceModel model){
        charityEventService.create(model);
        return super.redirect("/");
    }
}
