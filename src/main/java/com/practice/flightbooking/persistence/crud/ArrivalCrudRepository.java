package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.crudinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArrivalCrudRepository extends BasicCrudRepository<ArrivalFlightEntity, Integer> {

    List<ArrivalFlightEntity> findByStatus(boolean status);

    Optional<List<ArrivalFlightEntity>> findByIdAirportAndStatus(int idAirport, boolean status);

    Optional<List<ArrivalFlightEntity>> findByArrivalTimeAfterAndStatus(LocalDateTime arrivalTime, boolean status);

    @Modifying
    @Query("UPDATE ArrivalFlightEntity af SET af.status = false WHERE af.idArrivalFlight = :id")
    void updateArrivalStatus(@Param("id") int id);

}
