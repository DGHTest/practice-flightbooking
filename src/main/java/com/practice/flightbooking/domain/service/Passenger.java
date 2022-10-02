package com.practice.flightbooking.domain.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Passenger {

    private int passengerId;

    private String lastName;

    private String firstName;

    private LocalDateTime birthDate;

    private String email;

    private int telephoneNumber;

    private String country;

    private String state;

    private String city;

    private int passportNumber;

    private int expirationDate;

    private String nationality;

    private Travel travel;
}
