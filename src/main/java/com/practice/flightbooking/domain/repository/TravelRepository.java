package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.Travel;

import java.util.List;
import java.util.Optional;

public interface TravelRepository {

    List<Travel> getAllTravels();

    Optional<Travel> getTravelById(int travelId);

    Optional<List<Travel>> getByIdArrivalFlight(int arrivalId);

    Optional<List<Travel>> getByIdDeparture(int departureId);

    Travel saveTravel(Travel travel);
}
