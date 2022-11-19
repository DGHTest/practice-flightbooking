package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.domain.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public Airport getById(int id) throws Exception {
        return airportRepository.getById(id);
    }

    public List<Airport> getByCountry(String country) throws Exception {
        return airportRepository.getByCountry(country);
    }


    public List<Airport> getByState(String state) throws Exception {
        return airportRepository.getByState(state);
    }

    public List<Airport> getByCity(String city) throws Exception {
        return airportRepository.getByCity(city);
    }
}
