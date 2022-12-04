package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.service.ArrivalFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/arrivals")
public class ArrivalFlightController {

    @Autowired
    private ArrivalFlightService arrivalFlightService;

    @GetMapping
    public ResponseEntity<List<ArrivalFlight>> getAllArrivalFlights() {
        return new ResponseEntity<>(arrivalFlightService.getAllArrivalFlights(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ArrivalFlight> getArrivalById(@PathVariable("id") int arrivalId) {
        return arrivalFlightService.getArrivalById(arrivalId)
                .map(arrival -> new ResponseEntity<>(arrival, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/airport/{id}")
    public ResponseEntity<List<ArrivalFlight>> getByIdAirport(@PathVariable("id") int airportId) {
        return arrivalFlightService.getByIdAirport(airportId)
                .map(arrival -> new ResponseEntity<>(arrival, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/time/{time}")
    public ResponseEntity<List<ArrivalFlight>> getByArrivalTime(@PathVariable("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalTime) {
        return arrivalFlightService.getByArrivalTime(arrivalTime)
                .map(arrival -> new ResponseEntity<>(arrival, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<ArrivalFlight> saveArrival(@RequestBody ArrivalFlight arrivalFlight) {
        return new ResponseEntity<>(arrivalFlightService.saveArrival(arrivalFlight), HttpStatus.CREATED);
    }
}
