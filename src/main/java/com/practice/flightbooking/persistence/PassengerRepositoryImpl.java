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
    public Passenger getPassengerById(int passengerId) throws Exception {
        Optional<PassengerEntity> passengerById = passengerCrudRepository.findById(passengerId);

        if (passengerById.isPresent()) {
            return passengerMapper.toPassenger(passengerById.get());
        } else {
            throw new Exception("Passenger id not found");
        }
    }

    @Override
    public Passenger getPassengerByEmail(String email) throws Exception {
        Optional<PassengerEntity> passengerByEmail = passengerCrudRepository.findByEmail(email);

        if (passengerByEmail.isPresent()) {
            return passengerMapper.toPassenger(passengerByEmail.get());
        } else {
            throw new Exception("Email not found");
        }
    }

    @Override
    public Passenger savePassenger(Passenger passenger) throws Exception {
        PassengerEntity passengerEntity = passengerMapper.toPassengerEntity(passenger);

        if (!passengerCrudRepository.existsById(passengerEntity.getIdPassenger())) {
            return passengerMapper.toPassenger(passengerCrudRepository.save(passengerEntity));
        } else {
            throw new Exception("Id already exist");
        }
    }

    @Override
    public void updatePassengerStatusById(boolean status, int passengerId) throws Exception {
        if (passengerCrudRepository.existsById(passengerId)) {
            passengerCrudRepository.updatePassengerStatus(status, passengerId);
        } else {
            throw new Exception("Id not found");
        }
    }

    @Override
    public void deletePassenger(int passengerId) throws Exception {
        if (passengerCrudRepository.existsById(passengerId)) {
            passengerCrudRepository.save(PassengerEntity.builder()
                            .setIdPassenger(passengerId)
                            .setLastNames("Default is my last name")
                            .setFirstName("Default")
                            .setBirthDate(LocalDate.of(3000, 01, 01))
                            .setEmail("default" + passengerId + "@default.com")
                            .setTelephoneNumber("000000000000")
                            .setCountry("Default Country")
                            .setState("Default State")
                            .setCity("Default City")
                            .setPassportNumber(0000000000L + passengerId)
                            .setExpirationDate(LocalDate.of(2000, 01, 01))
                            .setNationality("DFT")
                            .setStatus(false)
                    .create());
        } else {
            throw new Exception("Id not found");
        }
    }
}
