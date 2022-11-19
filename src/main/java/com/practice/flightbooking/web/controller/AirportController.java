package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class AirportController {

    @Autowired
    private AirportService airportService;
}
