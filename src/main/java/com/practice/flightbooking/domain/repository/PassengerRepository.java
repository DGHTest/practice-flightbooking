package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.service.Passenger;
import com.practice.flightbooking.domain.service.Travel;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository {

    Optional<Passenger> getPassenger(int passengerId);

    Passenger save(Passenger passenger);

    void delete(int passengerId);

}
