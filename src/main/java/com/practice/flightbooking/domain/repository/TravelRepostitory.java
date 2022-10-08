package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.service.Travel;

import java.util.List;
import java.util.Optional;

public interface TravelRepostitory {

    List<Travel> getAllTravels();

    Optional<List<Travel>> getByArrivalFlight(int airportId);

    Optional<List<Travel>> getByDeparture(int departureFlightId);
}
