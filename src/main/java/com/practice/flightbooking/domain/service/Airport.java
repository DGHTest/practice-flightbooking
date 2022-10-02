package com.practice.flightbooking.domain.service;

import lombok.Data;

@Data
public class Airport {

    private int airportId;

    private String name;

    private String iata;

    private String country;

    private String city;

    private String timezone;
}
