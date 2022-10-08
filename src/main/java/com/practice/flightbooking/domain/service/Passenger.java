package com.practice.flightbooking.domain.service;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Passenger {

    private int passengerId;

    private int travelId;

    private String lastName;

    private String firstName;

    private LocalDateTime birthDate;

    private String email;

    private String telephoneNumber;

    private String country;

    private String state;

    private String city;

    private int passportNumber;

    private int expirationDate;

    private String nationality;

    private List<PassengersTravel> passengersTravel;

    private List<Ticket> tickets;
}
