package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.service.ArrivalFlight;
import com.practice.flightbooking.domain.service.Departure;

import java.util.List;

public interface DepartureRepository {

    List<Departure> getAllDepartures();

    Departure getDepartureById(int departureId) throws Exception;

    List<Departure> getByIdAirport(int airportId) throws Exception;

    Departure saveDeparture(Departure departure) throws Exception;
}
