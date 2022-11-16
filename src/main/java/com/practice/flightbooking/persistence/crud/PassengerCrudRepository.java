package com.practice.flightbooking.persistence.crud;

import com.practice.flightbooking.persistence.crud.crudinterfaces.BasicCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PassengerCrudRepository extends BasicCrudRepository<PassengerEntity, Integer> {

    Optional<PassengerEntity> findByEmail(String email);

    @Modifying
    @Query("UPDATE PassengerEntity AS p SET p.status = :s WHERE p.idPassenger = :id")
    void updatePassengerStatus(@Param("s") boolean status, @Param("id") int id);
}
