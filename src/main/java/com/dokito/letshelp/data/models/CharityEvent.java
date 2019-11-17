package com.dokito.letshelp.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "person_in_need_name")
    private PersonInNeed personInNeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cause_name")
    private Cause cause;
}
