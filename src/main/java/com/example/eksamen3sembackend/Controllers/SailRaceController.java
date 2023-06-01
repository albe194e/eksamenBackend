package com.example.eksamen3sembackend.Controllers;

import com.example.eksamen3sembackend.Models.Boat;
import com.example.eksamen3sembackend.Models.SailRace;
import com.example.eksamen3sembackend.Service.SailRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SailRaceController {


    @Autowired
    SailRaceService sailRaceService;


    //GET
    //get weekly sailraces
    @GetMapping("/api/get/weekly/sailraces")
    public List<SailRace> getWeeklySailRaces() {
        return sailRaceService.getWeeklySailRaces();
    }

    //Get  sailraces baeed on status (not finished/finished)
    @GetMapping("/api/get/sailrace/from/status/{value}")
    public List<SailRace> getSailRacesNotFinished(@PathVariable String value) {
        return sailRaceService.getSailRacesByStatus(value);
    }

    //Get all sailraces
    @GetMapping("/api/get/all/sailraces")
    public List<SailRace> getAllSailRaces() {
        return sailRaceService.getAllSailRaces();
    }

    //Get sailrace by id
    @GetMapping("/api/get/{id}/sailrace")
    public SailRace getSailRace(@PathVariable int id) {
        return sailRaceService.getSailRace(id);
    }


    //POST
    //Add boat to sailrace
    @PostMapping("/api/post/add/boat/{id}/sailrace/{sailRaceId}")
    public void addBoatToSailRace(@PathVariable int id, @PathVariable int sailRaceId) {
        sailRaceService.addBoatToSailRace(id, sailRaceId);
    }

    //Create one sailrace
    @PostMapping("/api/post/create/sailrace")
    public void createSailRace(@RequestBody SailRace sailRace) {
        sailRaceService.createSailRace(sailRace);
    }

    //Create a whole season of sailraces
    @PostMapping("/api/post/create/sailrace/season")
    public void createSailRaceSeason() {
        sailRaceService.createSailRaceSeason();

    }


    //PUT
    //Update sailrace to finished
    @PutMapping("/api/put/finish/sailrace/{id}")
    public void finishSailRace(@PathVariable int id) {
        sailRaceService.finishSailRace(id);
    }


    //DELETE
    //remove a boat from a sailrace
    @DeleteMapping("/api/delete/boat/{id}/sailrace/{sailRaceId}")
    public void deleteBoatFromSailRace(@PathVariable int id, @PathVariable int sailRaceId) {

        sailRaceService.deleteBoatFromSailRace(id, sailRaceId);
    }

}
