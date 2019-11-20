package com.dokito.letshelp.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "charity_event")
@Getter
@Setter
@NoArgsConstructor
public class CharityEvent extends BaseEntity {

    @Column(name = "charity_event_name")
    private String name;

    @Column
    private String description;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_in_need_id")
    private PersonInNeed personInNeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cause_id")
    private Cause cause;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User responsiblePerson;

    @ManyToMany(mappedBy = "eventsParticipating") //This creates a table for Users participating in event
    private List<User> participantsInEvent = new ArrayList<>();
}
