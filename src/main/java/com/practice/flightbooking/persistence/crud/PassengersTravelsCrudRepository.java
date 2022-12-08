package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.crudinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntityPk;

import java.util.List;
import java.util.Optional;

public interface PassengersTravelsCrudRepository extends BasicCrudRepository<PassengersTravelsEntity, PassengersTravelsEntityPk> {

    Optional<List<PassengersTravelsEntity>> findByPassengerTravelsIdIdPassenger(Integer idPassenger);

    Optional<List<PassengersTravelsEntity>> findByPassengerTravelsIdIdTravel(Integer idTravel);
}
