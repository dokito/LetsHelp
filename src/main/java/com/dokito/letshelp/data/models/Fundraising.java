package com.dokito.letshelp.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fundraising_event")
public class Fundraising extends BaseEntity {

    @Column(name = "fundraising_name")
    private String name;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cause_name")
    private Cause cause;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_in_need_name")
    private PersonInNeed personInNeed;

    @Column(name = "required_sum")
    private BigInteger requiredSum;

    @Column(name = "current_sum")
    private BigInteger currentSum;
}
