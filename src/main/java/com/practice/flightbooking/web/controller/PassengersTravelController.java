package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.PassengersTravel;
import com.practice.flightbooking.domain.service.PassengersTravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passenger-travels")
public class PassengersTravelController {

    @Autowired
    private PassengersTravelService passengersTravelService;

    @GetMapping("/{id}")
    public ResponseEntity<List<PassengersTravel>> getPassengersTravelByIdPassenger(@PathVariable("id") int passengerId) {
        return passengersTravelService.getPassengersTravelByIdPassenger(passengerId)
                .map(passengersTravel -> new ResponseEntity<>(passengersTravel, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save/{tId}-{pId}")
    public ResponseEntity<PassengersTravel> savePassengersTravel(@PathVariable("tId") int travelId, @PathVariable("pId") int passengerId) {
        return new ResponseEntity<>(passengersTravelService.savePassengersTravel(travelId, passengerId), HttpStatus.CREATED);
    }
}
