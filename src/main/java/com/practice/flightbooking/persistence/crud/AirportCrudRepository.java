package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.entity.AirportEntity;
import org.springframework.data.repository.CrudRepository;

public interface AirportCrudRepository extends CrudRepository<AirportEntity, Integer> {
}
