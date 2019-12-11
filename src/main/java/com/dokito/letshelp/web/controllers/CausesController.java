package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.service.models.create.CauseCreateServiceModel;
import com.dokito.letshelp.service.services.CauseService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/causes")
public class CausesController extends BaseController {

    private final CauseService causeService;
    private final ModelMapper mapper;

    public CausesController(CauseService causeService, ModelMapper mapper) {
        this.causeService = causeService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public ModelAndView getCreateCauseForm() {
        return super.view("causes/create_cause.html");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute CauseCreateServiceModel model) {
        causeService.create(model);
        return super.redirect("/");
    }

    @GetMapping("/all")
    public ModelAndView getAll(ModelAndView modelAndView){
        List<Cause> all = this.causeService.getAll();

        modelAndView.addObject("causes", all);

        return super.view("causes/causes_all.html", modelAndView);
    }

    @GetMapping("/details/{id}")
    public ModelAndView getDetails(@PathVariable String id, ModelAndView modelAndView){
        Cause byId = this.causeService.getById(id);

        modelAndView.addObject("cause", byId);

        return super.view("causes/cause_details.html", modelAndView);
    }
}
