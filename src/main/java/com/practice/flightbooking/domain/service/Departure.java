package com.practice.flightbooking.domain.service;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Departure {

    private int departureFlightId;

    private int airportId;

    private LocalDateTime departureTime;

    private Airport airport;

    private List<Travel> travel;
}
