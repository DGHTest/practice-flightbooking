package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.Travel;
import com.practice.flightbooking.domain.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping
    public ResponseEntity<List<Travel>> getAllTravels() {
        return new ResponseEntity<>(travelService.getAllTravels(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Travel> getTravelById(@PathVariable("id") int travelId) {
        return travelService.getTravelById(travelId)
                .map(travel -> new ResponseEntity<>(travel, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/arrival/{id}")
    public ResponseEntity<List<Travel>> getByIdArrivalFlight(@PathVariable("id") int arrivalId) {
        return travelService.getByIdArrivalFlight(arrivalId)
                .map(travels -> new ResponseEntity<>(travels, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/departure/{id}")
    public ResponseEntity<List<Travel>> getByIdDeparture(@PathVariable("id") int departureId) {
        return travelService.getByIdDeparture(departureId)
                .map(travels -> new ResponseEntity<>(travels, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<Travel> saveTravel(@RequestBody Travel travel) {
        return new ResponseEntity<>(travelService.saveTravel(travel), HttpStatus.CREATED);
    }
}
