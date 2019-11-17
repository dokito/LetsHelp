package com.dokito.letshelp.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person_in_need")
@Getter
@Setter
@NoArgsConstructor
public class PersonInNeed extends BaseEntity {

    @Column(name = "person_in_need_name", nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "personInNeed",
            cascade = CascadeType.ALL
    )
//    @JoinColumn(name = "fundraising_name")
    private List<Fundraising> fundraisingList = new ArrayList<>();

    @OneToMany(
            mappedBy = "personInNeed",
            cascade = CascadeType.ALL
    )
//    @JoinColumn(name = "charity_event_name")
    private List<CharityEvent> charityEventList = new ArrayList<>();

    @Column(name = "description_of_need")
    private String descriptionOfNeed;
}
