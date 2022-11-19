package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departures")
public class DepartureController {

    @Autowired
    private DepartureService departureService;
}
