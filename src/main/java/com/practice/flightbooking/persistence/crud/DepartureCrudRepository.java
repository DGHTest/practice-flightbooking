package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.crudinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DepartureCrudRepository extends BasicCrudRepository<DepartureEntity, Integer> {

    List<DepartureEntity> findByStatus(boolean status);

    Optional<List<DepartureEntity>> findByIdAirportAndStatus(int idAirport, boolean status);

    Optional<List<DepartureEntity>> findByDepartureTimeAfterAndStatus(LocalDateTime departureTime, boolean status);

    @Modifying
    @Transactional
    @Query("UPDATE DepartureEntity d SET d.status = false WHERE d.idDeparture = :id")
    void updateDepartureStatus(@Param("id") int id);
}
