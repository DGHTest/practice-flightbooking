package com.practice.flightbooking.domain.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Ticket {

    private int ticketId;

    private LocalDateTime boardingTime;

    private Passenger passenger;

}
