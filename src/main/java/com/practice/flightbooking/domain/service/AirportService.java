package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.domain.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public Optional<Airport> getById(int id) {
        return airportRepository.getById(id);
    }

    public Optional<List<Airport>> getByCountry(String country) {
        return airportRepository.getByCountry(country);
    }


    public Optional<List<Airport>> getByState(String state) {
        return airportRepository.getByState(state);
    }

    public Optional<List<Airport>> getByCity(String city) {
        return airportRepository.getByCity(city);
    }
}
