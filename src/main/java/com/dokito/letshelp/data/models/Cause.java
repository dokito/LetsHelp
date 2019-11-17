package com.dokito.letshelp.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "causes")
@Getter
@Setter
@NoArgsConstructor
public class Cause extends BaseEntity {

    @Column(name = "cause_name", unique = true, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "cause",
            cascade = CascadeType.ALL
    )
    private List<Fundraising> fundraisings;

    @OneToMany(
            mappedBy = "cause",
            cascade = CascadeType.ALL
    )
    private List<CharityEvent> charityEvents;

    private String requirements;
}
