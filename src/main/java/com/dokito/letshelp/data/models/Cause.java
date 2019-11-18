package com.dokito.letshelp.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<Fundraising> fundraisings = new ArrayList<>();

    @OneToMany(
            mappedBy = "cause",
            cascade = CascadeType.ALL
    )
    private List<CharityEvent> charityEvents = new ArrayList<>();

    private String requirements;
}
