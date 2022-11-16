package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.service.ArrivalFlight;

import java.util.List;
import java.util.Optional;


public interface ArrivalFlightRepository {

    List<ArrivalFlight> getAllArrivalFlights();

    ArrivalFlight getArrivalById(int arrivalId) throws Exception;

    List<ArrivalFlight> getByIdAirport(int airportId) throws Exception;

    ArrivalFlight saveArrival(ArrivalFlight arrivalFlight) throws Exception;
}
