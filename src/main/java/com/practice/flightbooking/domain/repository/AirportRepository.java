package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {

    Optional<Airport> getById(int id);

    Optional<List<Airport>> getByCountry(String country);

    Optional<List<Airport>> getByState(String state);

    Optional<List<Airport>> getByCity(String city);

}
