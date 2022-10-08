package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.domain.service.Passenger;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PassengerCrudRepository extends CrudRepository<PassengerEntity, Integer> {
    Optional<PassengerEntity> findByTravelTopByOrderByIdDesc();
}
