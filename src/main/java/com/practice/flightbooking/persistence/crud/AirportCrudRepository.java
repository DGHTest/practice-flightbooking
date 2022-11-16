package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.crudinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.AirportEntity;

import java.util.List;
import java.util.Optional;

public interface AirportCrudRepository extends BasicCrudRepository<AirportEntity, Integer> {

    Optional<List<AirportEntity>> findByCountry(String country);

    Optional<List<AirportEntity>> findByState(String state);

    Optional<List<AirportEntity>> findByCity(String city);
}
