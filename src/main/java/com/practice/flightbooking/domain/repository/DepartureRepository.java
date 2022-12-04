package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.Departure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DepartureRepository {

    List<Departure> getAllDepartures();

    Optional<Departure> getDepartureById(int departureId);

    Optional<List<Departure>> getByIdAirport(int airportId);

    Optional<List<Departure>> getByDepartureTime(LocalDateTime departureTime);

    Departure saveDeparture(Departure departure);
}
