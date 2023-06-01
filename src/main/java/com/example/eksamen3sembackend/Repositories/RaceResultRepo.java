package com.example.eksamen3sembackend.Repositories;

import com.example.eksamen3sembackend.Models.RaceResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RaceResultRepo extends JpaRepository<RaceResult, Integer> {


    Optional<RaceResult> findByBoatIdAndSailRaceId(int id, int sailRaceId);

    List<RaceResult> findAllBySailRaceId(int id);
}
