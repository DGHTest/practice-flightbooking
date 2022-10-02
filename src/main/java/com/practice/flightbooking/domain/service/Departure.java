package com.practice.flightbooking.domain.service;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Departure {

    private int departureFlightId;

    private LocalDateTime departureTime;

    private Airport airport;
}
