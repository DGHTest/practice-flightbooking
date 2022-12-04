package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.Departure;
import com.practice.flightbooking.domain.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/departures")
public class DepartureController {

    @Autowired
    private DepartureService departureService;

    @GetMapping
    public ResponseEntity<List<Departure>> getAllDepartures() {
        return new ResponseEntity<>(departureService.getAllDepartures(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departure> getDepartureById(@PathVariable("id") int departureId){
        return departureService.getDepartureById(departureId)
                .map(departure -> new ResponseEntity<>(departure, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/airport/{id}")
    public ResponseEntity<List<Departure>> getByIdAirport(@PathVariable("id") int airportId){
        return departureService.getByIdAirport(airportId)
                .map(departures -> new ResponseEntity<>(departures, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/time/{time}")
    public ResponseEntity<List<Departure>> getByDepartureTime(@PathVariable("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTime){
        return departureService.getByDepartureTime(departureTime)
                .map(departures -> new ResponseEntity<>(departures, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<Departure> saveDeparture(@RequestBody Departure departure){
        return new ResponseEntity<>(departureService.saveDeparture(departure), HttpStatus.CREATED);
    }
}
