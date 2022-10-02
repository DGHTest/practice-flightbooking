package com.practice.flightbooking.domain.service;

import lombok.Data;

@Data
public class Travel {

    private int travelId;

    private double price;

    private Departure departure;

    private ArrivalFlight arrivalFlight;
}
