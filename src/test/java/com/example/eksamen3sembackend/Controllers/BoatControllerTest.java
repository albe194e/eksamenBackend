package com.example.eksamen3sembackend.Controllers;

import com.example.eksamen3sembackend.Models.Boat;
import com.example.eksamen3sembackend.Models.BoatType;
import com.example.eksamen3sembackend.Models.SailRace;
import com.example.eksamen3sembackend.Repositories.BoatRepo;
import com.example.eksamen3sembackend.Repositories.SailRaceRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoatControllerTest {

    @Autowired
    BoatRepo boatRepo;
    @Autowired
    SailRaceRepo sailRaceRepo;

    @Test
    void getAllBoats() {

        //Instantiate boats
        Boat boat1 = new Boat();
        Boat boat2 = new Boat();

        boat1.setModel(BoatType.FOD25);
        boat2.setModel(BoatType.FOD40);

        boatRepo.save(boat1);
        boatRepo.save(boat2);


        List<Boat> boats = boatRepo.findAll();

        //Assert that there are 2 boats in database
        assertEquals(2, boats.size());


    }

    @Test
    void createBoat() {

        //Instantiate boat
        Boat boat1 = new Boat();
        boat1.setModel(BoatType.FOD25);
        boatRepo.save(boat1);

        //Assert that boat exists in database
        assertTrue(boatRepo.findById(1).isPresent());


    }

    @Test
    void updateBoat() {

        //Existing boat:
        Boat boat1 = new Boat();
        boat1.setModel(BoatType.FOD25);
        boatRepo.save(boat1);

        //Update boat to:
        Boat newBody = new Boat();
        newBody.setModel(BoatType.FOD40);

        //Update
        boat1.setModel(newBody.getModel());

        //assert with new type
        assertEquals(BoatType.FOD40, boat1.getModel());


    }

    @Test
    void deleteBoat() {

        //Instantiate boat
        Boat boat1 = new Boat();
        boat1.setModel(BoatType.FOD25);
        boatRepo.save(boat1);

        //Delete boat
        boatRepo.deleteById(1);

        //Assert that boat is deleted
        assertTrue(boatRepo.findById(1).isEmpty());

    }

    @Test
    void getAllBoatsInSailRace() {

        //Instantiate sailrace
        SailRace sailRace = new SailRace();
        sailRace.setModel(BoatType.FOD25);

        //Instantiate boats
        Boat boat1 = new Boat();
        Boat boat2 = new Boat();
        boat1.setModel(BoatType.FOD25);
        boat2.setModel(BoatType.FOD25);

        //Save boats
        boatRepo.save(boat1);
        boatRepo.save(boat2);

        //Add boats to sailrace
        sailRace.getBoats().add(boat1);
        sailRace.getBoats().add(boat2);

        //Save sailrace
        sailRaceRepo.save(sailRace);

        //Get boats from sailrace
        List<Boat> boats = boatRepo.getBoatsBySailRacesId(1);

        //Assert that there are 2 boats in sailrace
        assertEquals(2, boats.size());


    }

    @Test
    void getBoatsByModel() {

        //Instantiate boats
        Boat boat1 = new Boat();
        Boat boat2 = new Boat();
        boat1.setModel(BoatType.FOD25);
        boat2.setModel(BoatType.FOD25);

        //Save boats
        boatRepo.save(boat1);
        boatRepo.save(boat2);

        //Get boats by model
        List<Boat> boats = boatRepo.getBoatsByModel(BoatType.FOD25);

        //Assert that there are 2 boats with model FOD25
        assertEquals(2, boats.size());
    }

}