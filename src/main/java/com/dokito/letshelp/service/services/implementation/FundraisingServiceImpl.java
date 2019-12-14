package com.dokito.letshelp.service.services.implementation;

import com.dokito.letshelp.data.models.Fundraising;
import com.dokito.letshelp.data.repositories.FundraisingRepository;
import com.dokito.letshelp.errors.AddMoneyCannotBeNegative;
import com.dokito.letshelp.errors.Constants;
import com.dokito.letshelp.errors.FundrasingNotFound;
import com.dokito.letshelp.service.models.create.FundraisingCreateServiceModel;
import com.dokito.letshelp.service.models.create.FundraisingWithCause;
import com.dokito.letshelp.service.models.create.FundraisingWithPersonInNeed;
import com.dokito.letshelp.service.services.FundraisingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FundraisingServiceImpl implements FundraisingService {

    private final FundraisingRepository repository;
    private final ModelMapper mapper;

    public FundraisingServiceImpl(FundraisingRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Fundraising create(FundraisingWithCause withCause, FundraisingWithPersonInNeed withPersonInNeed) {
        Fundraising fundraising = null;
        if (withCause.getCause() != null && withPersonInNeed.getPersonInNeed() == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            fundraising = mapper.map(withCause, Fundraising.class);
            fundraising.setCurrentSum(BigDecimal.ZERO);
            fundraising.setStartDate(LocalDateTime.parse(withCause.getStartDate(), formatter));
            fundraising.setEndDate(LocalDateTime.parse(withCause.getEndDate(), formatter));
            repository.save(fundraising);
        } else if (withCause.getCause() == null && withPersonInNeed.getPersonInNeed() != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            fundraising = mapper.map(withPersonInNeed, Fundraising.class);
            fundraising.setCurrentSum(BigDecimal.ZERO);
            fundraising.setStartDate(LocalDateTime.parse(withPersonInNeed.getStartDate(), formatter));
            fundraising.setEndDate(LocalDateTime.parse(withPersonInNeed.getEndDate(), formatter));
            repository.save(fundraising);
        }

        return fundraising;
    }

    @Override
    public Fundraising getById(String id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new FundrasingNotFound(Constants.FUNDRAISING_ID_NOT_FOUND));
    }

    @Override
    public Fundraising addContribution(String id, String moneyToAdd) {
        Fundraising fundraising = this.repository.findById(id)
                .orElseThrow(() -> new FundrasingNotFound(Constants.FUNDRAISING_ID_NOT_FOUND));

        BigDecimal moneyToAddBigDecimal = new BigDecimal(moneyToAdd);

        if (moneyToAddBigDecimal.compareTo(BigDecimal.ZERO) == -1) {
            throw new AddMoneyCannotBeNegative(Constants.ADD_MONEY_CANNOT_BE_NEGATIVE);
        } else {
            fundraising.setCurrentSum(fundraising.getCurrentSum().add(moneyToAddBigDecimal));
        }

        this.repository.saveAndFlush(fundraising);

        return fundraising;
    }

    @Override
    public List<Fundraising> getAll() {
        return this.repository.findAll();
    }
}
