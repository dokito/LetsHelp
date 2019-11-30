package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.service.models.create.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.models.view.CharityEventViewDetailsModel;
import com.dokito.letshelp.service.services.CharityEventService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public ModelAndView getCreateCharityEventForm() {
        return super.view("charityEvents/create_charity_event.html");
    }

    @PostMapping("/create")
    public ModelAndView create(
            @RequestParam("startDate")
                                   @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm")
                                           String startDate,
                               @ModelAttribute CharityEventCreateServiceModel model) {

        charityEventService.create(model);
        return super.redirect("/");
    }

    @GetMapping("/all")
    public ModelAndView getAllCharityEvents(ModelAndView modelAndView){
        List<CharityEventCreateServiceModel> allCharityEvents = this.charityEventService.getAllCharityEvents();

        modelAndView.addObject("charityEvents", allCharityEvents);

        return super.view("charityEvents/charity_events_all.html", modelAndView);
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable String id,ModelAndView modelAndView){
        CharityEventViewDetailsModel charityEventById = this.charityEventService.getCharityEventById(id);

        modelAndView.addObject("charityEvent",charityEventById);

        return super.view("charityEvents/charity_event_details.html", modelAndView);
    }
}
