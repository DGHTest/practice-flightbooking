package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.Departure;

import java.time.LocalDateTime;
import java.util.List;

public interface DepartureRepository {

    List<Departure> getAllDepartures();

    Departure getDepartureById(int departureId) throws Exception;

    List<Departure> getByIdAirport(int airportId) throws Exception;

    List<Departure> getByDepartureTime(LocalDateTime departureTime) throws Exception;

    Departure saveDeparture(Departure departure) throws Exception;
}
