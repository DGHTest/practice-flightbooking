package com.practice.flightbooking.persistence;


import com.practice.flightbooking.domain.repository.TravelRepostitory;
import com.practice.flightbooking.domain.service.Travel;
import com.practice.flightbooking.persistence.crud.TravelCrudRepository;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import com.practice.flightbooking.persistence.mapper.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TravelRepositoryImpl implements TravelRepostitory {

    @Autowired
    private TravelCrudRepository travelCrudRepository;

    @Autowired
    private TravelMapper travelMapper;

    @Override
    public List<Travel> getAllTravels() {
        List<TravelEntity> travels = (List<TravelEntity>) travelCrudRepository.findAll();
        return travelMapper.toTravels(travels);
    }

    @Override
    public Optional<List<Travel>> getByArrivalFlight(int airportId) {
        List<TravelEntity> travels = travelCrudRepository.findByIdArrivalFlight(airportId);
        return Optional.of(travelMapper.toTravels(travels));
    }

    @Override
    public Optional<List<Travel>> getByDeparture(int departureId) {
        List<TravelEntity> travels = travelCrudRepository.findByIdDeparture(departureId);
        return Optional.of(travelMapper.toTravels(travels));
    }
}
