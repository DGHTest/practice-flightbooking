package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Travel;
import com.practice.flightbooking.domain.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    public List<Travel> getAllTravels() {
        return travelRepository.getAllTravels();
    }

    public Travel getTravelById(int travelId) throws Exception {
        return travelRepository.getTravelById(travelId);
    }

    public List<Travel> getByIdArrivalFlight(int arrivalId) throws Exception {
        return travelRepository.getByIdArrivalFlight(arrivalId);
    }

    public List<Travel> getByIdDeparture(int departureId) throws Exception {
        return travelRepository.getByIdDeparture(departureId);
    }

    public Travel saveTravel(Travel travel) throws Exception {
        return travelRepository.saveTravel(travel);
    }
}
