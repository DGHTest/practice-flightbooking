package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArrivalCrudRepository extends CrudRepository<ArrivalFlightEntity, Integer> {
}
