package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.service.models.PersonInNeedCreateServiceModel;
import com.dokito.letshelp.service.services.PersonInNeedService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pin")
public class PersonInNeedController extends BaseController {

    private final PersonInNeedService personInNeedService;
    private final ModelMapper mapper;

    public PersonInNeedController(PersonInNeedService personInNeedService, ModelMapper mapper) {
        this.personInNeedService = personInNeedService;
        this.mapper = mapper;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPersonInNeedForm(){
        return super.view("pin/create_person_in_need.html");
    }

    @PostMapping("/register")
    public ModelAndView registerPersonInNeed(@ModelAttribute PersonInNeedCreateServiceModel model){
        personInNeedService.register(model);
        return super.redirect("/");
    }
}
