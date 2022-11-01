package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.repositoryinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;

public interface AirportCrudRepository extends BasicCrudRepository<AirportEntity, Integer> {

    Optional<List<AirportEntity>> findByCountry(String country);

    Optional<AirportEntity> findByState(String state);

    Optional<AirportEntity> findByCity(String city);
}
