package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.service.Travel;

import java.util.List;

public interface TravelRepository {

    List<Travel> getAllTravels();

    Travel getTravelById(int travelId) throws Exception;

    List<Travel> getByIdArrivalFlight(int airportId) throws Exception;

    List<Travel> getByIdDeparture(int departureId) throws Exception;

    Travel saveTravel(Travel travel) throws Exception;
}
