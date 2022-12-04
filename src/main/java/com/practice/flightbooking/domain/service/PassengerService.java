package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.domain.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Optional<Passenger> getPassengerById(int passengerId){
        return passengerRepository.getPassengerById(passengerId);
    }

    public Optional<Passenger> getPassengerByEmail(String email) {
        return passengerRepository.getPassengerByEmail(email);
    }

    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.savePassenger(passenger);
    }

    public boolean updatePassengerStatusById(boolean status, int passengerId){
        if (getPassengerById(passengerId).isPresent()){
                passengerRepository.updatePassengerStatusById(status, passengerId);
                return true;
        } else {return false;}
    }

    public boolean deletePassenger(int passengerId) {
        if (getPassengerById(passengerId).isPresent()){
            passengerRepository.deletePassenger(passengerId);
            return true;
        } else {return false;}
    }
}
