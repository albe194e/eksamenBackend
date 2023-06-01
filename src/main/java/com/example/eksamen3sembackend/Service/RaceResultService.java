package com.example.eksamen3sembackend.Service;

import com.example.eksamen3sembackend.Models.Boat;
import com.example.eksamen3sembackend.Models.RaceResult;
import com.example.eksamen3sembackend.Repositories.BoatRepo;
import com.example.eksamen3sembackend.Repositories.RaceResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceResultService {

    @Autowired
    RaceResultRepo raceResultRepo;

    @Autowired
    BoatRepo boatRepo;


    public void updateRaceResult(int id, int value) {

        Optional<RaceResult> raceResultOptional = raceResultRepo.findById(id);

        if (raceResultOptional.isPresent()) {

            RaceResult raceResult = raceResultOptional.get();

            raceResult.setResult(value);

            Boat boat = raceResult.getBoat();
            boat.addToTotalPoints(value);
            boatRepo.save(boat);

            raceResultRepo.save(raceResult);

        }

    }

    public RaceResult getRaceResult(int id, int id2) {

        Optional<RaceResult> raceResult = raceResultRepo.findByBoatIdAndSailRaceId(id, id2);

        return raceResult.orElse(null);
    }

    public List<RaceResult> getResultsFromRace(int id) {

        return raceResultRepo.findAllBySailRaceId(id);

    }
}
