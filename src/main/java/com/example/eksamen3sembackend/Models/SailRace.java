package com.example.eksamen3sembackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SailRace {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date;


    @OneToMany(mappedBy = "sailRace")
    private Set<RaceResult> raceResults = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL})
    @JoinTable(
            name = "sailrace_boats",
            joinColumns = @JoinColumn(name = "sailrace_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "boat_id"),
            }
    )
    @EqualsAndHashCode.Exclude
    private Set<Boat> boats = new HashSet<>();


    private BoatType model;

    private boolean isFinished;

    public void addBoat(Boat boat) {
        boats.add(boat);

    }
}
