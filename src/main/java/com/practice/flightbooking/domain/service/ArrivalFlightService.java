package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.repository.ArrivalFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArrivalFlightService {

    @Autowired
    private ArrivalFlightRepository arrivalFlightRepository;

    public List<ArrivalFlight> getAllArrivalFlights() {
        return arrivalFlightRepository.getAllArrivalFlights();
    }

    public ArrivalFlight getArrivalById(int arrivalId) throws Exception {
        return arrivalFlightRepository.getArrivalById(arrivalId);
    }

    public List<ArrivalFlight> getByIdAirport(int airportId) throws Exception {
        return arrivalFlightRepository.getByIdAirport(airportId);
    }

    public List<ArrivalFlight> getByArrivalTime(LocalDateTime arrivalTime) throws Exception {
        return arrivalFlightRepository.getByArrivalTime(arrivalTime);
    }

    public ArrivalFlight saveArrival(ArrivalFlight arrivalFlight) throws Exception {
        return arrivalFlightRepository.saveArrival(arrivalFlight);
    }
}
