package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.domain.service.Travel;
import com.practice.flightbooking.persistence.entity.TicketEntity;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TicketCrudRepository extends CrudRepository<TicketEntity, Integer> {

    Optional<List<TravelEntity>> findByIdArrivalFlightAndEstado();
}
