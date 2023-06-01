package com.example.eksamen3sembackend.Repositories;

import com.example.eksamen3sembackend.Models.Boat;
import com.example.eksamen3sembackend.Models.SailRace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SailRaceRepo extends JpaRepository<SailRace, Integer> {


    List<SailRace> findSailRaceByisFinished(boolean finished);

}
