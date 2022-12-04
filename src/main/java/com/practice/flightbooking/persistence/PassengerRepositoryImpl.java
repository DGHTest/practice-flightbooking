package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.PassengerRepository;
import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.persistence.crud.PassengerCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import com.practice.flightbooking.persistence.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class PassengerRepositoryImpl implements PassengerRepository {

    @Autowired
    private PassengerCrudRepository passengerCrudRepository;

    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public Optional<Passenger> getPassengerById(int passengerId) {
        return passengerCrudRepository.findById(passengerId)
                .map(passenger -> passengerMapper.toPassenger(passenger));
    }

    @Override
    public Optional<Passenger> getPassengerByEmail(String email) {
        return passengerCrudRepository.findByEmail(email)
                .map(passenger -> passengerMapper.toPassenger(passenger));
    }

    @Override
    public Passenger savePassenger(Passenger passenger) {
        PassengerEntity passengerEntity = passengerMapper.toPassengerEntity(passenger);
        return passengerMapper.toPassenger(passengerCrudRepository.save(passengerEntity));
    }

    @Override
    public void updatePassengerStatusById(boolean status, int passengerId) {
        passengerCrudRepository.updatePassengerStatus(status, passengerId);
    }

    @Override
    public void deletePassenger(int passengerId) {
            passengerCrudRepository.save(PassengerEntity.builder()
                            .setIdPassenger(passengerId)
                            .setLastNames("Default is my last name")
                            .setFirstName("Default")
                            .setBirthDate(LocalDate.of(3000, 01, 01))
                            .setEmail("default" + passengerId + "@default.com")
                            .setTelephoneNumber("000000000000" + passengerId)
                            .setCountry("Default Country")
                            .setState("Default State")
                            .setCity("Default City")
                            .setPassportNumber(passengerId + 0000000000L)
                            .setExpirationDate(LocalDate.of(2000, 01, 01))
                            .setNationality("DFT")
                            .setStatus(false)
                    .create());
    }
}
