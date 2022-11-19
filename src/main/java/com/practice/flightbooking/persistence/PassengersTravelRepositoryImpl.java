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
    public List<PassengersTravel> getPassengersTravelByIdPassenger(int passengerId) throws Exception {
        Optional<List<PassengersTravelsEntity>> passengersTravelsByIdPassenger =
                passengersTravelsCrudRepository.findByPassengerTravelsIdIdPassenger(passengerId);

        if (passengersTravelsByIdPassenger.isPresent()) {
            return passengersTravelMapper.toPassengersTravels(passengersTravelsByIdPassenger.get());
        } else {
            throw new Exception("Passenger id not found");
        }
    }

    @Override
    public PassengersTravel savePassengersTravel(int travelId, int passengerId) throws Exception {
        PassengersTravelsEntityPk passengersTravelsEntityPk = PassengersTravelsEntityPk.builder()
                .setIdTravel(travelId)
                .setIdPassenger(passengerId)
                .create();

        if (!passengersTravelsCrudRepository.existsById(passengersTravelsEntityPk)) {
            PassengersTravelsEntity passengersTravelsEntity = PassengersTravelsEntity.builder()
                    .setPassengerTravelsId(passengersTravelsEntityPk).create();

            return passengersTravelMapper.toPassengersTravel(passengersTravelsCrudRepository.save(passengersTravelsEntity));
        } else {
            throw new Exception("Id already exist");
        }
    }
}
