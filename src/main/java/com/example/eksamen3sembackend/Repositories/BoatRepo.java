package com.example.eksamen3sembackend.Repositories;

import com.example.eksamen3sembackend.Models.Boat;
import com.example.eksamen3sembackend.Models.BoatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoatRepo extends JpaRepository<Boat, Integer> {

    List<Boat> getBoatsBySailRacesId(int id);

    List<Boat> getBoatsByModel(BoatType model);

    List<Boat> findAllByModelOrderByTotalPointsAsc(BoatType model);

}
