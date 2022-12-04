package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.PassengersTravelRepository;
import com.practice.flightbooking.domain.PassengersTravel;
import com.practice.flightbooking.persistence.crud.PassengersTravelsCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntityPk;
import com.practice.flightbooking.persistence.mapper.PassengersTravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PassengersTravelRepositoryImpl implements PassengersTravelRepository {

    @Autowired
    private PassengersTravelsCrudRepository passengersTravelsCrudRepository;

    @Autowired
    private PassengersTravelMapper passengersTravelMapper;

    @Override
    public Optional<List<PassengersTravel>> getPassengersTravelByIdPassenger(int passengerId) {
        return passengersTravelsCrudRepository.findByPassengerTravelsIdIdPassenger(passengerId)
                .map(passenger -> passengersTravelMapper.toPassengersTravels(passenger));
    }

    @Override
    public PassengersTravel savePassengersTravel(int travelId, int passengerId) {
        PassengersTravelsEntityPk passengersTravelsEntityPk = PassengersTravelsEntityPk.builder()
                .setIdTravel(travelId)
                .setIdPassenger(passengerId)
                .create();

        PassengersTravelsEntity passengersTravelsEntity = PassengersTravelsEntity.builder()
                    .setPassengerTravelsId(passengersTravelsEntityPk).create();

            return passengersTravelMapper.toPassengersTravel(passengersTravelsCrudRepository.save(passengersTravelsEntity));
    }
}
