package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.ArrivalFlight;

import java.time.LocalDateTime;
import java.util.List;


public interface ArrivalFlightRepository {

    List<ArrivalFlight> getAllArrivalFlights();

    ArrivalFlight getArrivalById(int arrivalId) throws Exception;

    List<ArrivalFlight> getByIdAirport(int airportId) throws Exception;

    List<ArrivalFlight> getByArrivalTime(LocalDateTime arrivalTime) throws Exception;

    ArrivalFlight saveArrival(ArrivalFlight arrivalFlight) throws Exception;
}
