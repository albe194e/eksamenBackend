package com.example.eksamen3sembackend.Controllers;

import com.example.eksamen3sembackend.Models.RaceResult;
import com.example.eksamen3sembackend.Service.RaceResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RaceResultController {

    @Autowired
    RaceResultService raceResultService;


    //GET
    //get standing for a boat in a sailrace
    @GetMapping("/api/get/raceresult/boat/{id}/sailrace/{id2}")
    public RaceResult getRaceResult(@PathVariable int id, @PathVariable int id2) {
        return raceResultService.getRaceResult(id, id2);
    }

    //get all standings for a sailrace
    @GetMapping("/api/get/results/race/{id}")
    public List<RaceResult> getResultsFromRace(@PathVariable int id) {
        return raceResultService.getResultsFromRace(id);
    }


    //PUT
    //update standing for a boat in a sailrace
    @PutMapping("/api/put/{id}/raceresult/{value}")
    public void updateRaceResult(@PathVariable int id, @PathVariable int value) {

        raceResultService.updateRaceResult(id, value);

    }


}
