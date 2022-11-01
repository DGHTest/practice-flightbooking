package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.service.Departure;

import java.util.List;

public interface DepartureRepository {

    List<Departure> getAllDepartures();
}
