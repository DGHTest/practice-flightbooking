package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.ArrivalFlight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ArrivalFlightRepository {

    List<ArrivalFlight> getAllArrivalFlights();

    Optional<ArrivalFlight> getArrivalById(int arrivalId);

    Optional<List<ArrivalFlight>> getByIdAirport(int airportId);

    Optional<List<ArrivalFlight>> getByArrivalTime(LocalDateTime arrivalTime);

    ArrivalFlight saveArrival(ArrivalFlight arrivalFlight);
}
