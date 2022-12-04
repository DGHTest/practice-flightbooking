package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.PassengersTravel;

import java.util.List;
import java.util.Optional;

public interface PassengersTravelRepository {

    Optional<List<PassengersTravel>> getPassengersTravelByIdPassenger(int passengerId);

    PassengersTravel savePassengersTravel(int travelId, int passengerId);
}
