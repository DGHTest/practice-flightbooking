package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.entity.DepartureEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepartureCrudRepository extends CrudRepository<DepartureEntity, Integer> {
}
