package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.Passenger;

import java.util.Optional;

public interface PassengerRepository {

    Optional<Passenger> getPassengerById(int passengerId);

    Optional<Passenger> getPassengerByEmail(String email);

    Passenger savePassenger(Passenger passenger);

    void updatePassengerStatusById(boolean status, int passengerId);

    void deletePassenger(int passengerId);

}
