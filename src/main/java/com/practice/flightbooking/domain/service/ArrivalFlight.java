package com.practice.flightbooking.domain.service;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArrivalFlight {

    private int arrivalFlightId;

    private int airportId;

    private LocalDateTime arrivalTime;

    private Airport airport;

    private List<Travel> travel;
}
