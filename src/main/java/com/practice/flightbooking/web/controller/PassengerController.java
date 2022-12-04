package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.domain.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.Optional;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable("id") int passengerId){
        return passengerService.getPassengerById(passengerId)
                .map(passenger -> new ResponseEntity<>(passenger, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Passenger> getPassengerByEmail(@PathVariable String email) {
        return passengerService.getPassengerByEmail(email)
                .map(passenger -> new ResponseEntity<>(passenger, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<Passenger> savePassenger(@RequestBody Passenger passenger) {
        return new ResponseEntity<>(passengerService.savePassenger(passenger), HttpStatus.CREATED);

    }

    @PutMapping("/update-status/{id}/{status}")
    public ResponseEntity updatePassengerStatusById(@PathVariable boolean status, @PathVariable("id") int passengerId){
        if (passengerService.updatePassengerStatusById(status, passengerId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePassenger(@PathVariable("id") int passengerId) {
        if (passengerService.deletePassenger(passengerId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
