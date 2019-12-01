package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.repositories.CharityEventRepository;
import com.dokito.letshelp.data.repositories.UserRepository;
import com.dokito.letshelp.errors.CharityEventNotFound;
import com.dokito.letshelp.errors.Constants;
import com.dokito.letshelp.service.models.CharityEventServiceModel;
import com.dokito.letshelp.service.models.create.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.models.edit.CharityEventEditServiceModel;
import com.dokito.letshelp.service.models.view.CharityEventViewDetailsModel;
import com.dokito.letshelp.service.models.view.UserViewModel;
import com.dokito.letshelp.service.services.CharityEventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharityEventServiceImpl implements CharityEventService {

    private final CharityEventRepository charityEventRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public CharityEventServiceImpl(CharityEventRepository charityEventRepository, UserRepository userRepository, ModelMapper mapper) {
        this.charityEventRepository = charityEventRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public CharityEvent create(CharityEventCreateServiceModel model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String date = model.getStartDate();
        CharityEvent charityEvent = mapper.map(model, CharityEvent.class);
        charityEvent.setStartDate(LocalDateTime.parse(date, formatter));
        charityEventRepository.saveAndFlush(charityEvent);
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
}
