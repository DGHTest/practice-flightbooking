package com.practice.flightbooking.persistence;


import com.practice.flightbooking.domain.repository.TravelRepository;
import com.practice.flightbooking.domain.service.Travel;
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
    public Travel getTravelById(int travelId) throws Exception {
        Optional<TravelEntity> travelById = travelCrudRepository.findById(travelId);

        if (travelById.isPresent()) {
            return travelMapper.toTravel(travelById.get());
        } else {
            throw new Exception("Travel by id not found");
        }
    }

    @Override
    public List<Travel> getByIdArrivalFlight(int airportId) throws Exception {
        Optional<List<TravelEntity>> travelsByArrival = travelCrudRepository.findByIdArrivalFlightAndStatus(airportId, true);

        if (travelsByArrival.isPresent()) {
            return travelMapper.toTravels(travelsByArrival.get());
        } else {
            throw new Exception("Arrival flight id not found");
        }
    }

    @Override
    public List<Travel> getByIdDeparture(int departureId) throws Exception {
        Optional<List<TravelEntity>> travelsByArrival = travelCrudRepository.findByIdDepartureAndStatus(departureId, true);

        if (travelsByArrival.isPresent()) {
            return travelMapper.toTravels(travelsByArrival.get());
        } else {
            throw new Exception("Departure id not found");
        }
    }

    @Override
    public Travel saveTravel(Travel travel) throws Exception {
        TravelEntity travelEntity = travelMapper.toTravelEntity(travel);

        if (!travelCrudRepository.existsById(travelEntity.getIdTravel())) {
            return travelMapper.toTravel(travelCrudRepository.save(travelEntity));
        } else {
            throw new Exception("Id already exist");
        }
    }
}
