package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.Airport;

import java.util.List;

public interface AirportRepository {

    Airport getById(int id) throws Exception;

    List<Airport> getByCountry(String country) throws Exception;

    List<Airport> getByState(String state) throws Exception;

    List<Airport> getByCity(String city) throws Exception;

}
