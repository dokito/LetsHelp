package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.service.models.create.CauseCreateServiceModel;
import com.dokito.letshelp.service.services.CauseService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
