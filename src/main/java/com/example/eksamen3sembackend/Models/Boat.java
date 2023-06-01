package com.example.eksamen3sembackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Boat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BoatType model;

    private int totalPoints;

    @ManyToMany(mappedBy = "boats", fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL})
    @JsonIgnore
    private Set<SailRace> sailRaces = new HashSet<>();

    @OneToMany(mappedBy = "boat")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<RaceResult> raceResults = new HashSet<>();

    public void addToTotalPoints(int points) {
        this.totalPoints += points;
    }

}
