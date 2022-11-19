package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.PassengersTravel;

import java.util.List;

public interface PassengersTravelRepository {

    List<PassengersTravel> getPassengersTravelByIdPassenger(int passengerId) throws Exception;

    PassengersTravel savePassengersTravel(int travelId, int passengerId) throws Exception;
}
