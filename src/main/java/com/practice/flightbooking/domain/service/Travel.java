package com.practice.flightbooking.domain.service;

import lombok.Data;

@Data
public class Travel {

    private int travelId;

    private int arrivalFlightId;

    private int departureId;

    private int passengerId;

    private double price;

    private ArrivalFlight arrivalFlight;

    private Departure departure;

}
