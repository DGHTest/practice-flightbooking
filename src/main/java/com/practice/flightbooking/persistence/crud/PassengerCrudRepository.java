package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.repositoryinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengerEntity;

import java.util.Optional;

public interface PassengerCrudRepository extends BasicCrudRepository<PassengerEntity, Integer> {

    Optional<PassengerEntity> findByEmail(String email);
}
