package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.service.ArrivalFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arrivals")
public class ArrivalFlightController {

    @Autowired
    private ArrivalFlightService arrivalFlightService;
}
