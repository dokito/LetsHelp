package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.data.repositories.CharityEventRepository;
import com.dokito.letshelp.data.repositories.UserRepository;
import com.dokito.letshelp.errors.CharityEventNotFound;
import com.dokito.letshelp.errors.Constants;
import com.dokito.letshelp.errors.UserNotFound;
import com.dokito.letshelp.service.models.CharityEventServiceModel;
import com.dokito.letshelp.service.models.create.CharityEventCreateModelWithCause;
import com.dokito.letshelp.service.models.create.CharityEventCreateModelWithPersonInNeed;
import com.dokito.letshelp.service.models.create.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.models.edit.CharityEventEditServiceModel;
import com.dokito.letshelp.service.models.view.UserViewModel;
import com.dokito.letshelp.service.services.CharityEventService;
import com.dokito.letshelp.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharityEventServiceImpl implements CharityEventService {

    private final CharityEventRepository charityEventRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper mapper;

    public CharityEventServiceImpl(CharityEventRepository charityEventRepository, UserRepository userRepository, UserService userService, ModelMapper mapper) {
        this.charityEventRepository = charityEventRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public CharityEvent create(CharityEventCreateModelWithCause modelWithCause, CharityEventCreateModelWithPersonInNeed modelWithPersonInNeed) {
        CharityEvent charityEvent = null;
        if (modelWithCause.getCause() != null && modelWithPersonInNeed.getPersonInNeed() == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            String date = modelWithCause.getStartDate();
            String endDate = modelWithCause.getEndDate();
            charityEvent = mapper.map(modelWithCause, CharityEvent.class);
            charityEvent.setStartDate(LocalDateTime.parse(date, formatter));
            charityEvent.setEndDate(LocalDateTime.parse(endDate, formatter));
            charityEventRepository.saveAndFlush(charityEvent);
        } else if (modelWithPersonInNeed.getPersonInNeed() != null && modelWithCause.getCause() == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            String date = modelWithPersonInNeed.getStartDate();
            String endDate = modelWithPersonInNeed.getEndDate();
            charityEvent = mapper.map(modelWithPersonInNeed, CharityEvent.class);
            charityEvent.setStartDate(LocalDateTime.parse(date, formatter));
            charityEvent.setEndDate(LocalDateTime.parse(endDate, formatter));
            charityEventRepository.saveAndFlush(charityEvent);
        }
        return charityEvent;
    }

    @Override
    public List<CharityEventCreateServiceModel> getAllCharityEvents() {
        return this.charityEventRepository.findAll()
                .stream()
                .map(charityEvent -> mapper.map(charityEvent, CharityEventCreateServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CharityEventServiceModel getCharityEventById(String id) {
        return this.charityEventRepository.findById(id)
                .map(charityEvent -> mapper.map(charityEvent, CharityEventServiceModel.class))
                .orElseThrow(() -> new CharityEventNotFound(Constants.CHARITY_EVENT_ID_NOT_FOUND));
    }

    @Override
    public CharityEventEditServiceModel editCharityEvent(String id, CharityEventEditServiceModel model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        CharityEvent charityEvent = this.charityEventRepository.findById(id)
                .orElseThrow(() -> new CharityEventNotFound(Constants.CHARITY_EVENT_ID_NOT_FOUND));

        charityEvent.setName(model.getName());
        charityEvent.setDescription(model.getDescription());
        charityEvent.setStartDate(LocalDateTime.parse(model.getStartDate(), formatter));
        charityEvent.setEndDate(LocalDateTime.parse(model.getEndDate(), formatter));
//        charityEvent.setCause(model.getCause());
//        charityEvent.setPersonInNeed(model.getPersonInNeed());
        charityEvent.setResponsiblePerson(model.getResponsiblePerson());

        this.charityEventRepository.saveAndFlush(charityEvent);

        return mapper.map(charityEvent, CharityEventEditServiceModel.class);
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CharityEventEditServiceModel addParticipant(String id, CharityEventEditServiceModel model, UserViewModel participant) {
        CharityEvent charityEvent = charityEventRepository.findById(id)
                .map(c -> mapper.map(c, CharityEvent.class))
                .orElseThrow(() -> new CharityEventNotFound(Constants.CHARITY_EVENT_ID_NOT_FOUND));
        if (model.getParticipantsInEvent().size() == 0) {
            model.setParticipantsInEvent(new ArrayList<>());
        }
        model.getParticipantsInEvent().add(mapper.map(participant, User.class));

        charityEvent.setParticipantsInEvent(model.getParticipantsInEvent());

        charityEventRepository.save(charityEvent);

        return mapper.map(charityEvent, CharityEventEditServiceModel.class);
    }

    @Override
    public User getUserById(String id) {
        return this.userRepository.getById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
