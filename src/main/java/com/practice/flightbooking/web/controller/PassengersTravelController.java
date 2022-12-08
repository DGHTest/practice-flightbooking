package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.PassengersTravel;
import com.practice.flightbooking.domain.service.PassengersTravelService;
import com.practice.flightbooking.persistence.crud.PassengersTravelsCrudRepository;
import com.practice.flightbooking.persistence.crud.TravelCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.web.controller.precondition.CapacityTravel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/passenger-travels")
public class PassengersTravelController {

    @Autowired
    private PassengersTravelService passengersTravelService;

    @Autowired
    private CapacityTravel capacityTravel;

    @GetMapping("/{id}")
    public ResponseEntity<List<PassengersTravel>> getPassengersTravelByIdPassenger(@PathVariable("id") int passengerId) {
        return passengersTravelService.getPassengersTravelByIdPassenger(passengerId)
                .map(passengersTravel -> new ResponseEntity<>(passengersTravel, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save/{pId}-{tId}")
    public ResponseEntity<?> savePassengersTravel(@PathVariable("tId") int travelId, @PathVariable("pId") int passengerId) {
        if (capacityTravel.capacity(travelId)) {
            return new ResponseEntity<>(passengersTravelService.savePassengersTravel(travelId, passengerId), HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("Capacity in travel: " + travelId + " has reached the limit");
    }
}
