package com.example.eksamen3sembackend.Service;

import com.example.eksamen3sembackend.Models.Boat;
import com.example.eksamen3sembackend.Models.BoatType;
import com.example.eksamen3sembackend.Repositories.BoatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatService {


    @Autowired
    BoatRepo boatRepo;


    public List<Boat> getAllBoats() {


        return boatRepo.findAll();
    }

    public void createBoat(Boat boat) {

        boatRepo.save(boat);

    }

    public void updateBoat(Boat newBody, int id) {

        Optional<Boat> boatOptional = boatRepo.findById(id);

        if (boatOptional.isPresent()) {

            Boat boat = boatOptional.get();

            boat.setModel(newBody.getModel());

            boatRepo.save(boat);
        }

    }

    public List<Boat> getAllBoatsInSailRace(int id) {

        return boatRepo.getBoatsBySailRacesId(id);

    }

    public void deleteBoat(int id) {

        boatRepo.deleteById(id);
    }

    public List<Boat> getBoatsByModel(BoatType model) {

        return boatRepo.getBoatsByModel(model);
    }

    public List<Boat> getBoatsByModelOrdered(BoatType boatType) {

        return boatRepo.findAllByModelOrderByTotalPointsAsc(boatType);

    }
}
