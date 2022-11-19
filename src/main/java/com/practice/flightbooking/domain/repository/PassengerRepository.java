package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.Passenger;

public interface PassengerRepository {

    Passenger getPassengerById(int passengerId) throws Exception;

    Passenger getPassengerByEmail(String email)throws Exception;

    Passenger savePassenger(Passenger passenger) throws Exception;

    void updatePassengerStatusById(boolean status, int passengerId) throws Exception;

    void deletePassenger(int passengerId) throws Exception;

}
