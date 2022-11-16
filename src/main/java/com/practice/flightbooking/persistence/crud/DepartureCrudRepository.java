package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.crudinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DepartureCrudRepository extends BasicCrudRepository<DepartureEntity, Integer> {

    Optional<List<DepartureEntity>> findByIdAirportAndStatus(int idAirport, boolean status);

    List<DepartureEntity> findByStatus(boolean status);

    @Modifying
    @Query("UPDATE DepartureEntity d SET d.status = false WHERE d.idDeparture = :id")
    void updateDepartureStatus(@Param("id") int id);
}
