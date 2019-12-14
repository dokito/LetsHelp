package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.service.models.PersonInNeedCreateServiceModel;
import com.dokito.letshelp.service.services.PersonInNeedService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        return super.redirect("/pin/all");
    }

    @GetMapping("/all")
    public ModelAndView getAll(ModelAndView modelAndView){
        List<PersonInNeed> all = this.personInNeedService.getAll();

        modelAndView.addObject("personsInNeed", all);

        return super.view("pin/all_pin.html", modelAndView);
    }

    @GetMapping("/details/{id}")
    public ModelAndView getDetails(@PathVariable String id, ModelAndView modelAndView){
        PersonInNeed byId = this.personInNeedService.findById(id);

        modelAndView.addObject("pin", byId);

        return super.view("pin/pin_details.html", modelAndView);
    }
}
