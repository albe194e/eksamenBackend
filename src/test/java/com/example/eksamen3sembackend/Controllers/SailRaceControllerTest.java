package com.example.eksamen3sembackend.Controllers;

import com.example.eksamen3sembackend.Models.Boat;
import com.example.eksamen3sembackend.Models.BoatType;
import com.example.eksamen3sembackend.Models.SailRace;
import com.example.eksamen3sembackend.Repositories.BoatRepo;
import com.example.eksamen3sembackend.Repositories.SailRaceRepo;
import org.hibernate.annotations.NaturalId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SailRaceControllerTest {

    @Autowired
    SailRaceRepo sailRaceRepo;
    @Autowired
    BoatRepo boatRepo;

    @Test
    void deleteBoatFromSailRace() {

        //Save a boat
        Boat boat1 = new Boat();
        boat1.setModel(BoatType.FOD25);
        boatRepo.save(boat1);

        //Save a sailrace and the boat to it
        SailRace sailRace1 = new SailRace();
        sailRace1.getBoats().add(boat1);
        sailRaceRepo.save(sailRace1);

        //Remove the boat from the sailrace
        sailRace1.getBoats().remove(boat1);
        sailRaceRepo.save(sailRace1);

        //Assert that the boat is removed from the sailrace
        assertEquals(0, sailRace1.getBoats().size());

    }


    @Test
    void getSailRacesNotFinished() {

        SailRace sailRace = new SailRace();
        sailRace.setFinished(false);

        sailRaceRepo.save(sailRace);

        assertEquals(1, sailRaceRepo.findSailRaceByisFinished(false).size());
    }

    @Test
    void finishSailRace() {


        SailRace sailRace = new SailRace();
        sailRace.setFinished(false);

        sailRaceRepo.save(sailRace);

        sailRace.setFinished(true);

        sailRaceRepo.save(sailRace);

        assertTrue(sailRaceRepo.findById(1).get().isFinished());

    }

}