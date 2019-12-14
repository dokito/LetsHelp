package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.data.models.Fundraising;
import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.service.models.create.FundraisingWithCause;
import com.dokito.letshelp.service.models.create.FundraisingWithPersonInNeed;
import com.dokito.letshelp.service.models.edit.FundraisingAddMoneyModel;
import com.dokito.letshelp.service.services.CauseService;
import com.dokito.letshelp.service.services.FundraisingService;
import com.dokito.letshelp.service.services.PersonInNeedService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/fundraisings")
public class FundraisingController extends BaseController {

    private final FundraisingService fundraisingService;
    private final ModelMapper mapper;
    private final CauseService causeService;
    private final PersonInNeedService personInNeedService;

    public FundraisingController(FundraisingService fundraisingService, ModelMapper mapper, CauseService causeService, PersonInNeedService personInNeedService) {
        this.fundraisingService = fundraisingService;
        this.mapper = mapper;
        this.causeService = causeService;
        this.personInNeedService = personInNeedService;
    }

    @GetMapping("/create")
    public ModelAndView getCreateFundraisingForm(ModelAndView modelAndView){
        List<Cause> allCauses = this.causeService.getAll();
        List<PersonInNeed> allPeopleInNeed = this.personInNeedService.getAll();

        modelAndView.addObject("allCauses", allCauses);
        modelAndView.addObject("allPin", allPeopleInNeed);

        return super.view("fundraisings/create_fundraising.html", modelAndView);
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute FundraisingWithCause withCause, FundraisingWithPersonInNeed withPersonInNeed){
        fundraisingService.create(withCause, withPersonInNeed);
        return super.redirect("/fundraisings/all");
    }

    @GetMapping("/add_contribution/{id}")
    public ModelAndView getContributeMoney(@PathVariable String id, ModelAndView modelAndView){
        FundraisingAddMoneyModel byId = mapper.map(this.fundraisingService.getById(id), FundraisingAddMoneyModel.class);

        modelAndView.addObject("fundraising", byId);

        return super.view("/fundraisings/add_contribution.html", modelAndView);
    }

    @PostMapping("/add_contribution/{id}")
    public ModelAndView addContribution(@PathVariable String id, @ModelAttribute FundraisingAddMoneyModel fundraising){
        this.fundraisingService.addContribution(id, fundraising.getAddMoney());

        return super.redirect("/fundraisings/details/" + id);
    }

    @GetMapping("/details/{id}")
    public ModelAndView getDetails(@PathVariable String id, ModelAndView modelAndView){
        Fundraising byId = this.fundraisingService.getById(id);

        modelAndView.addObject("fundraising", byId);

        return super.view("/fundraisings/fundraising_details.html", modelAndView);
    }

    @GetMapping("/all")
    public ModelAndView getAll(ModelAndView modelAndView){
        List<Fundraising> allFundraisings = this.fundraisingService.getAll();

        modelAndView.addObject("fundraisings", allFundraisings);

        return super.view("fundraisings/all_fundraisings.html", modelAndView);
    }
}
