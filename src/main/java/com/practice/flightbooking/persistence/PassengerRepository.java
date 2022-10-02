package com.practice.flightbooking.persistence;

import com.practice.flightbooking.persistence.crud.PassengerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerRepository {

    @Autowired
    private PassengerCrudRepository passengerCrudRepository;
}
