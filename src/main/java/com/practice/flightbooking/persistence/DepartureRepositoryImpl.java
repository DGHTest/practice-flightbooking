package com.practice.flightbooking.persistence;

import com.practice.flightbooking.persistence.crud.DepartureCrudRepository;
import com.practice.flightbooking.persistence.mapper.DepartureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartureRepositoryImpl {

    @Autowired
    private DepartureCrudRepository departureCrudRepository;

    @Autowired
    private DepartureMapper departureMapper;
}
