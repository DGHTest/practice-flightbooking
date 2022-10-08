package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.domain.service.Travel;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TravelCrudRepository extends CrudRepository<TravelEntity, Integer> {

    List<TravelEntity> findByIdArrivalFlight(int arrivalFlightId);

    List<TravelEntity> findByIdDeparture(int departureId);
}
