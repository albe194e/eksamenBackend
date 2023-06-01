package com.example.eksamen3sembackend.Controllers;

import com.example.eksamen3sembackend.Models.Boat;
import com.example.eksamen3sembackend.Models.BoatType;
import com.example.eksamen3sembackend.Service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BoatController {


    @Autowired
    BoatService boatService;

    //GET
    //Get all boats in a sailrace
    @GetMapping("/api/get/all/boats/sailrace/{id}")
    public List<Boat> getAllBoatsInSailRace(@PathVariable int id) {
        return boatService.getAllBoatsInSailRace(id);
    }

    //Get boats based on model
    @GetMapping("/api/get/boats/model/{model}")
    public List<Boat> getBoatsByModel(@PathVariable String model) {

        BoatType boatType = BoatType.valueOf(model);

        return boatService.getBoatsByModel(boatType);
    }

    //Get boats based on model, ordered
    @GetMapping("/api/get/boats/model/{model}/ordered")
    public List<Boat> getBoatsByModelOrdered(@PathVariable String model) {

        BoatType boatType = BoatType.valueOf(model);

        return boatService.getBoatsByModelOrdered(boatType);
    }

    //Get all boats
    @GetMapping("/api/get/all/boats")
    public List<Boat> getAllBoats() {
        return boatService.getAllBoats();
    }


    //POST
    //Create boat
    @PostMapping("/api/post/create/boat")
    public void createBoat(@RequestBody Boat boat) {
        boatService.createBoat(boat);
    }


    //PUT
    //Update boat
    @PutMapping("/api/put/{id}/boat")
    public void updateBoat(@RequestBody Boat newBody, @PathVariable int id) {
        boatService.updateBoat(newBody, id);
    }


    //DELETE
    //Delete boat
    @DeleteMapping("/api/delete/{id}/boat")
    public void deleteBoat(@PathVariable int id) {
        boatService.deleteBoat(id);
    }


}
