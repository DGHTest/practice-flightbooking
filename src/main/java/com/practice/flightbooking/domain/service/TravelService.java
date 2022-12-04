package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Travel;
import com.practice.flightbooking.domain.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    public List<Travel> getAllTravels() {
        return travelRepository.getAllTravels();
    }

    public Optional<Travel> getTravelById(int travelId) {
        return travelRepository.getTravelById(travelId);
    }

    public Optional<List<Travel>> getByIdArrivalFlight(int arrivalId) {
        return travelRepository.getByIdArrivalFlight(arrivalId);
    }

    public Optional<List<Travel>> getByIdDeparture(int departureId) {
        return travelRepository.getByIdDeparture(departureId);
    }

    public Travel saveTravel(Travel travel) {
        return travelRepository.saveTravel(travel);
    }
}
