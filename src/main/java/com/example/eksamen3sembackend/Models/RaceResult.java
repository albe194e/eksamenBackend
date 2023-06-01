package com.example.eksamen3sembackend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RaceResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int result;

    @ManyToOne
    private Boat boat;

    @ManyToOne
    private SailRace sailRace;

    public RaceResult(int result) {
        this.result = result;
    }

}
