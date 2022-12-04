package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.domain.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/places")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/airport/{id}")
    public ResponseEntity<Airport> getById(@PathVariable("id") int airportId) {
        return airportService.getById(airportId)
                .map(airport -> new ResponseEntity<>(airport, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Integer>> getByCountry(@PathVariable("country") String country) {
        return airportService.getByCountry(country)
                .map(airport -> new ResponseEntity<>(
                        airport.stream().map(Airport::getAirportId).collect(Collectors.toList()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/state/{state}")
    public ResponseEntity<List<Integer>> getByState(@PathVariable("state") String state) {
        return airportService.getByState(state)
                .map(airport -> new ResponseEntity<>(
                        airport.stream().map(Airport::getAirportId).collect(Collectors.toList()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Integer>> getByCity(@PathVariable("city") String city) {
        return airportService.getByCity(city)
                .map(airport -> new ResponseEntity<>(
                        airport.stream().map(Airport::getAirportId).collect(Collectors.toList()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
