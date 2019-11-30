package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.CharityEvent;
import com.dokito.letshelp.data.repositories.CharityEventRepository;
import com.dokito.letshelp.errors.CharityEventNotFound;
import com.dokito.letshelp.errors.Constants;
import com.dokito.letshelp.service.models.create.CharityEventCreateServiceModel;
import com.dokito.letshelp.service.models.view.CharityEventViewDetailsModel;
import com.dokito.letshelp.service.services.CharityEventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharityEventServiceImpl implements CharityEventService {

    private final CharityEventRepository repository;
    private final ModelMapper mapper;

    public CharityEventServiceImpl(CharityEventRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CharityEvent create(CharityEventCreateServiceModel model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String date = model.getStartDate();
        CharityEvent charityEvent = mapper.map(model, CharityEvent.class);
        charityEvent.setStartDate(LocalDateTime.parse(date, formatter));
        repository.saveAndFlush(charityEvent);
        return charityEvent;
    }

    @Override
    public List<CharityEventCreateServiceModel> getAllCharityEvents() {
        return this.repository.findAll()
                .stream()
                .map(charityEvent -> mapper.map(charityEvent, CharityEventCreateServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CharityEventViewDetailsModel getCharityEventById(String id) {
        return this.repository.findById(id)
                .map(charityEvent -> mapper.map(charityEvent, CharityEventViewDetailsModel.class))
                .orElseThrow(() -> new CharityEventNotFound(Constants.CHARITY_EVENT_ID_NOT_FOUND));
    }
}
