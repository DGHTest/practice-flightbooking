package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Departure;
import com.practice.flightbooking.domain.repository.DepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartureService {

    @Autowired
    private DepartureRepository departureRepository;

    public List<Departure> getAllDepartures() {
        return departureRepository.getAllDepartures();
    }

    public Departure getDepartureById(int departureId) throws Exception {
        return departureRepository.getDepartureById(departureId);
    }

    public List<Departure> getByIdAirport(int airportId) throws Exception {
        return departureRepository.getByIdAirport(airportId);
    }

    public List<Departure> getByDepartureTime(LocalDateTime departureTime) throws Exception {
        return departureRepository.getByDepartureTime(departureTime);
    }

    public Departure saveDeparture(Departure departure) throws Exception {
        return departureRepository.saveDeparture(departure);
    }
}
