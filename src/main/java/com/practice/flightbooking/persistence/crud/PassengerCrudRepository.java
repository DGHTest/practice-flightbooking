package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.entity.PassengerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PassengerCrudRepository extends CrudRepository<PassengerEntity, Integer> {


}
