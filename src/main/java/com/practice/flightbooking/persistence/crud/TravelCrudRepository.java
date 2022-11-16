package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.crudinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TravelCrudRepository extends BasicCrudRepository<TravelEntity, Integer> {

    Optional<List<TravelEntity>> findByIdArrivalFlightAndStatus(int arrivalFlightId, boolean status);

    Optional<List<TravelEntity>> findByIdDepartureAndStatus(int departureId, boolean status);

    List<TravelEntity> findByStatus(boolean status);

    @Modifying
    @Query("UPDATE TravelEntity as t SET t.status = false WHERE t.idArrivalFlight = :id")
    void updateTravelStatus(@Param("id") Integer id);
}
