package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.domain.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger getPassengerById(int passengerId) throws Exception {
        return passengerRepository.getPassengerById(passengerId);
    }

    public Passenger getPassengerByEmail(String email)throws Exception {
        return passengerRepository.getPassengerByEmail(email);
    }

    public Passenger savePassenger(Passenger passenger) throws Exception {
        return passengerRepository.savePassenger(passenger);
    }

    public boolean updatePassengerStatusById(boolean status, int passengerId) throws Exception {
        try {
            passengerRepository.updatePassengerStatusById(status, passengerId);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }

        return false;
    }

    public boolean deletePassenger(int passengerId) throws Exception {
        try {
            passengerRepository.deletePassenger(passengerId);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }

        return false;
    }
}
