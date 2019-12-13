package com.dokito.letshelp.web.controllers;

import com.dokito.letshelp.data.models.Cause;
import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.models.PersonInNeed;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.data.repositories.UserRepository;
import com.dokito.letshelp.service.models.CharityEventServiceModel;
import com.dokito.letshelp.service.models.LoginUserServiceModel;
import com.dokito.letshelp.service.models.create.CharityEventCreateModelWithCause;
import com.dokito.letshelp.service.models.create.CharityEventCreateModelWithPersonInNeed;
import com.dokito.letshelp.service.models.create.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.models.edit.CharityEventEditServiceModel;
import com.dokito.letshelp.service.models.view.UserViewModel;
import com.dokito.letshelp.service.services.CauseService;
import com.dokito.letshelp.service.services.CharityEventService;
import com.dokito.letshelp.service.services.PersonInNeedService;
import com.dokito.letshelp.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/charityEvents")
public class CharityEventController extends BaseController {

    private final CharityEventService charityEventService;
    private final CauseService causeService;
    private final PersonInNeedService personInNeedService;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public CharityEventController(CharityEventService charityEventService, CauseService causeService, PersonInNeedService personInNeedService, ModelMapper mapper, UserRepository userRepository) {
        this.charityEventService = charityEventService;
        this.causeService = causeService;
        this.personInNeedService = personInNeedService;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @GetMapping("/create")
    @PreAuthorize(value = "hasRole('ROLE_CONTRIBUTOR')")
    public ModelAndView getCreateCharityEventForm(ModelAndView modelAndView) {
        List<UserViewModel> allUsers = this.charityEventService.getAllUsers();
        List<Cause> allCauses = this.causeService.getAll();
        List<PersonInNeed> allPin = this.personInNeedService.getAll();

        modelAndView.addObject("allUsers", allUsers);
        modelAndView.addObject("allCauses", allCauses);
        modelAndView.addObject("allPin", allPin);

        return super.view("charityEvents/create_charity_event.html", modelAndView);
    }

    @PostMapping("/create")
    public ModelAndView create(
//            @PathVariable ModelAndView modelAndView,
//            @RequestParam("startDate")
//            @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm")
//                    String startDate,
            @ModelAttribute CharityEventCreateModelWithCause modelWithCause, CharityEventCreateModelWithPersonInNeed modelWithPersonInNeed) {
//
//        List<UserViewModel> allUsers = this.charityEventService.getAllUsers();
//
//        modelAndView.addObject("allUsers", allUsers);

        charityEventService.create(modelWithCause, modelWithPersonInNeed);
        return super.redirect("/charityEvents/all");
    }

    @GetMapping("/all")
    public ModelAndView getAllCharityEvents(ModelAndView modelAndView) {
        List<CharityEventCreateServiceModel> allCharityEvents = this.charityEventService.getAllCharityEvents();

        modelAndView.addObject("charityEvents", allCharityEvents);

        return super.view("charityEvents/charity_events_all.html", modelAndView);
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable String id, ModelAndView modelAndView) {
        HttpSession session = session();
        LoginUserServiceModel user = (LoginUserServiceModel) session.getAttribute("user");
        CharityEventServiceModel charityEventById = this.charityEventService.getCharityEventById(id);

        modelAndView.addObject("charityEvent", charityEventById);
        modelAndView.addObject("participants", charityEventById.getParticipantsInEvent());
        modelAndView.addObject("user", user);

        return super.view("charityEvents/charity_event_details.html", modelAndView);
    }

    @PostMapping("/details/{id}")
    public ModelAndView detailsAddParticipant(@PathVariable String id, @ModelAttribute CharityEventEditServiceModel charityEvent) {
        HttpSession session = session();
        CharityEventServiceModel charityEventById = this.charityEventService.getCharityEventById(id);
        LoginUserServiceModel user = (LoginUserServiceModel) session.getAttribute("user");
        User userById = this.userRepository.findByUsername(user.getUsername());
        userById.getEventsParticipating().add(mapper.map(charityEvent, CharityEvent.class));

//        User userToAdd = this.charityEventService.getUserByUsername(user.getUsername());
        this.charityEventService.addParticipant(id, mapper.map(charityEventById, CharityEventEditServiceModel.class), mapper.map(userById, UserViewModel.class));

        return super.redirect("/charityEvents/details/" + id);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ModelAndView editCharityEvent(@PathVariable String id, ModelAndView modelAndView) {
        CharityEventServiceModel model = mapper.map(this.charityEventService.getCharityEventById(id), CharityEventServiceModel.class);
        List<UserViewModel> allUsers = this.charityEventService.getAllUsers();

        modelAndView.addObject("charityEventId", model.getId());
        modelAndView.addObject("charityEvent", model);
        modelAndView.addObject("allUsers", allUsers);

        return super.view("charityEvents/charity_event_edit.html", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editCharityEventConfirm(@PathVariable String id, @ModelAttribute CharityEventEditServiceModel model) {
        this.charityEventService.editCharityEvent(id, mapper.map(model, CharityEventEditServiceModel.class));

        return super.redirect("/charityEvents/details/" + id);
    }

    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
}
