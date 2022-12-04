package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.repository.ArrivalFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArrivalFlightService {

    @Autowired
    private ArrivalFlightRepository arrivalFlightRepository;

    public List<ArrivalFlight> getAllArrivalFlights() {
        return arrivalFlightRepository.getAllArrivalFlights();
    }

    public Optional<ArrivalFlight> getArrivalById(int arrivalId) {
        return arrivalFlightRepository.getArrivalById(arrivalId);
    }

    public Optional<List<ArrivalFlight>> getByIdAirport(int airportId) {
        return arrivalFlightRepository.getByIdAirport(airportId);
    }

    public Optional<List<ArrivalFlight>> getByArrivalTime(LocalDateTime arrivalTime) {
        return arrivalFlightRepository.getByArrivalTime(arrivalTime);
    }

    public ArrivalFlight saveArrival(ArrivalFlight arrivalFlight) {
        return arrivalFlightRepository.saveArrival(arrivalFlight);
    }
}
