package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Departure;
import com.practice.flightbooking.domain.repository.DepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartureService {

    @Autowired
    private DepartureRepository departureRepository;

    public List<Departure> getAllDepartures() {
        return departureRepository.getAllDepartures();
    }

    public Optional<Departure> getDepartureById(int departureId){
        return departureRepository.getDepartureById(departureId);
    }

    public Optional<List<Departure>> getByIdAirport(int airportId){
        return departureRepository.getByIdAirport(airportId);
    }

    public Optional<List<Departure>> getByDepartureTime(LocalDateTime departureTime){
        return departureRepository.getByDepartureTime(departureTime);
    }

    public Departure saveDeparture(Departure departure){
        return departureRepository.saveDeparture(departure);
    }
}
