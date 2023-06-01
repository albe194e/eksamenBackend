package com.example.eksamen3sembackend.Service;

import com.example.eksamen3sembackend.Models.Boat;
import com.example.eksamen3sembackend.Models.BoatType;
import com.example.eksamen3sembackend.Models.RaceResult;
import com.example.eksamen3sembackend.Models.SailRace;
import com.example.eksamen3sembackend.Repositories.BoatRepo;
import com.example.eksamen3sembackend.Repositories.RaceResultRepo;
import com.example.eksamen3sembackend.Repositories.SailRaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SailRaceService {


    @Autowired
    SailRaceRepo sailRaceRepo;
    @Autowired
    BoatRepo boatRepo;
    @Autowired
    RaceResultRepo raceResultRepo;

    public void createSailRaceSeason() {


        Calendar startdate = Calendar.getInstance();
        startdate.set(2023, Calendar.MAY, 3);

        Calendar enddate = Calendar.getInstance();
        enddate.set(2023, Calendar.SEPTEMBER, 27);

        ArrayList<BoatType> boatTypes = new ArrayList<>();
        boatTypes.add(BoatType.FOD25);
        boatTypes.add(BoatType.FOD25TIL40);
        boatTypes.add(BoatType.FOD40);


        ArrayList<String> dates = new ArrayList<>();

        //How many weeks ?
        int startWeek = startdate.get(Calendar.WEEK_OF_YEAR);
        int endWeek = enddate.get(Calendar.WEEK_OF_YEAR);
        int weeks = endWeek - startWeek;


        //Start with the startdate
        Calendar currentDate = startdate;
        dates.add(String.valueOf(currentDate.getTime()));

        //Find the dates for each week
        for (int i = 0; i < weeks; i++) {

            currentDate.add(Calendar.DAY_OF_MONTH, +7);
            System.out.println("This is inside the loop" + currentDate.getTime());
            dates.add(String.valueOf(currentDate.getTime()));

        }


        //Create a sailrace for each week
        for (int i = 0; i < boatTypes.size(); i++) {

            for (int j = 0; j < dates.size(); j++) {

                System.out.println(dates.get(j));

                //Instantiate a new sailrace with the boattype and date
                SailRace sailRace = new SailRace();


                sailRace.setModel(boatTypes.get(i));

                //add a week to the date
                sailRace.setDate(String.valueOf(dates.get(j)));

                System.out.println(sailRace);

                //Save the sailrace to the database
                sailRaceRepo.save(sailRace);

            }
        }
    }

    public void createSailRace(SailRace sailRace) {

        sailRaceRepo.save(sailRace);
    }

    public List<SailRace> getAllSailRaces() {

        return sailRaceRepo.findAll();
    }

    public void addBoatToSailRace(int id, int sailRaceId) {

        //Find the boat and sailrace by id
        Optional<Boat> optionalBoat = boatRepo.findById(id);
        Optional<SailRace> optionalSailRace = sailRaceRepo.findById(sailRaceId);

        //add the boat to the sailrace they exist
        if (optionalBoat.isPresent() && optionalSailRace.isPresent()) {

            Boat boat = optionalBoat.get();
            SailRace sailRace = optionalSailRace.get();

            //check if the model of the boat is the same as the model of the sailrace
            if (optionalBoat.get().getModel() == optionalSailRace.get().getModel()) {

                RaceResult raceResult = new RaceResult(0);

                raceResult.setSailRace(sailRace);
                raceResult.setBoat(boat);

                raceResultRepo.save(raceResult);
                optionalSailRace.get().addBoat(optionalBoat.get());

                //save the sailrace
                sailRaceRepo.save(optionalSailRace.get());
            }
        }
    }

    public SailRace getSailRace(int id) {

        return sailRaceRepo.findById(id).orElse(null);

    }


    public void deleteBoatFromSailRace(int id, int sailRaceId) {

        Optional<SailRace> sailRaceOptional = sailRaceRepo.findById(sailRaceId);
        Optional<Boat> boatOptional = boatRepo.findById(id);
        Optional<RaceResult> raceResultOptional = raceResultRepo.findByBoatIdAndSailRaceId(id, sailRaceId);

        if (sailRaceOptional.isPresent() && boatOptional.isPresent()) {
            SailRace sailRace = sailRaceOptional.get();
            Boat boat = boatOptional.get();

            Set<Boat> boats = sailRace.getBoats();
            boats.remove(boat);
            sailRace.setBoats(boats);

            Set<SailRace> sailRaces = boat.getSailRaces();
            sailRaces.remove(sailRace);
            boat.setSailRaces(sailRaces);

            raceResultRepo.delete(raceResultOptional.get());
            boatRepo.save(boat);
            sailRaceRepo.save(sailRace);

        }
    }

    public List<SailRace> getWeeklySailRaces() {

        List<SailRace> sailRaces = sailRaceRepo.findAll();


        //Find the sailraces that are in the current week
        List<SailRace> weeklySailRaces = new ArrayList<>();

        //Used to parse the date to a calendar object
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        for (SailRace sailRace : sailRaces) {

            Calendar sailRaceDate = Calendar.getInstance();

            try {
                sailRaceDate.setTime(format.parse(sailRace.getDate()));
            } catch (Exception e) {
                e.printStackTrace();
            }


            Calendar currentDate = Calendar.getInstance();

            if (sailRaceDate.get(Calendar.WEEK_OF_YEAR) == currentDate.get(Calendar.WEEK_OF_YEAR)) {

                weeklySailRaces.add(sailRace);
            }
        }

        return weeklySailRaces;

    }

    public List<SailRace> getSailRacesByStatus(String status) {

        return switch (status) {
            case "finished" -> sailRaceRepo.findSailRaceByisFinished(true);
            case "notfinished" -> sailRaceRepo.findSailRaceByisFinished(false);
            default -> new ArrayList<>();
        };
    }

    public void finishSailRace(int id) {

        Optional<SailRace> optionalSailRace = sailRaceRepo.findById(id);

        if (optionalSailRace.isPresent()) {

            optionalSailRace.get().setFinished(true);
            sailRaceRepo.save(optionalSailRace.get());
        }

    }
}
