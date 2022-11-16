package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.crudinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.TicketEntity;

import java.util.List;
import java.util.Optional;

public interface TicketCrudRepository extends BasicCrudRepository<TicketEntity, Integer> {

    Optional<List<TicketEntity>> findByIdPassenger(int passengerId);
}
