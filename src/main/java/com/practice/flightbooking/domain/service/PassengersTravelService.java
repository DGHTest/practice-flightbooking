package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.domain.PassengersTravel;
import com.practice.flightbooking.domain.repository.PassengersTravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengersTravelService {

    @Autowired
    private PassengersTravelRepository passengersTravelRepository;

    public Optional<List<PassengersTravel>> getPassengersTravelByIdPassenger(int passengerId) {
        return passengersTravelRepository.getPassengersTravelByIdPassenger(passengerId);
    }

    public PassengersTravel savePassengersTravel(int travelId, int passengerId) {
        return passengersTravelRepository.savePassengersTravel(travelId, passengerId);
    }
}
