package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    private TravelService travelService;
}
