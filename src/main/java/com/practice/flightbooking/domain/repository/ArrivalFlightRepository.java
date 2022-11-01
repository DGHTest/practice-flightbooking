package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.service.ArrivalFlight;

import java.util.List;
import java.util.Optional;

public interface ArrivalFlightRepository {

    Optional<ArrivalFlight> getArrivalById(int arrivalId);
    List<ArrivalFlight> getAllArrivalFlights();

    ArrivalFlight saveArrival(ArrivalFlight arrivalFlight);
}
