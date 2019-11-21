package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.service.models.CauseCreateServiceModel;
import com.dokito.letshelp.service.services.CauseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/causes")
public class CausesController {

    private final CauseService causeService;
    private final ModelMapper mapper;

    public CausesController(CauseService causeService, ModelMapper mapper) {
        this.causeService = causeService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public String getRegisterForm() {
        return "causes/create_cause.html";
    }

    @PostMapping("/create")
    public String register(@ModelAttribute CauseCreateServiceModel model) {
        CauseCreateServiceModel serviceModel = mapper.map(model, CauseCreateServiceModel.class);
        causeService.create(serviceModel);
        return "redirect:/";
    }
}
