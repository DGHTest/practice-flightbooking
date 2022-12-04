package com.practice.flightbooking.persistence;


import com.practice.flightbooking.domain.repository.TravelRepository;
import com.practice.flightbooking.domain.Travel;
import com.practice.flightbooking.persistence.crud.TravelCrudRepository;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import com.practice.flightbooking.persistence.mapper.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TravelRepositoryImpl implements TravelRepository {

    @Autowired
    private TravelCrudRepository travelCrudRepository;

    @Autowired
    private TravelMapper travelMapper;

    @Override
    public List<Travel> getAllTravels() {
        List<TravelEntity> allTravels = travelCrudRepository.findByStatus(true);
        return travelMapper.toTravels(allTravels);
    }

    @Override
    public Optional<Travel> getTravelById(int travelId) {
        return travelCrudRepository.findById(travelId)
                .map(travel -> travelMapper.toTravel(travel));
    }

    @Override
    public Optional<List<Travel>> getByIdArrivalFlight(int arrivalId) {
        return travelCrudRepository.findByIdArrivalFlightAndStatus(arrivalId, true)
                .map(travels -> travelMapper.toTravels(travels));
    }

    @Override
    public Optional<List<Travel>> getByIdDeparture(int departureId) {
        return travelCrudRepository.findByIdDepartureAndStatus(departureId, true)
                .map(travels -> travelMapper.toTravels(travels));
    }

    @Override
    public Travel saveTravel(Travel travel) {
        TravelEntity travelEntity = travelMapper.toTravelEntity(travel);
        return travelMapper.toTravel(travelCrudRepository.save(travelEntity));
    }
}
