package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.PassengerRepository;
import com.practice.flightbooking.domain.service.Passenger;
import com.practice.flightbooking.persistence.crud.PassengerCrudRepository;
import com.practice.flightbooking.persistence.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PassengerRepositoryImpl implements PassengerRepository {

    @Autowired
    private PassengerCrudRepository passengerCrudRepository;

    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public Optional<Passenger> getPassenger(int passengerId) {
        return Optional.empty();
    }

    @Override
    public Passenger save(Passenger passenger) {
        return null;
    }

    @Override
    public void delete(int passengerId) {

    }

    @Override
    public Optional<Passenger> findLastTravel() {
        return Optional.empty();
    }

}
