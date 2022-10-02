package com.practice.flightbooking.domain.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArrivalFlight {

    private int arrivalFlightId;

    private LocalDateTime arrivalTime;

    private Airport airport;
}
