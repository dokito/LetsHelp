package com.dokito.letshelp.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(
            mappedBy = "responsiblePerson",
            cascade = CascadeType.ALL
    )
    private List<CharityEvent> eventsResponsible = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "events_participating",
            joinColumns = @JoinColumn(name = "charity_event_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private List<CharityEvent> eventsParticipating = new ArrayList<>();
}
