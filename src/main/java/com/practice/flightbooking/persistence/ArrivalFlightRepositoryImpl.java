package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.ArrivalFlightRepository;
import com.practice.flightbooking.domain.service.ArrivalFlight;
import com.practice.flightbooking.persistence.crud.ArrivalCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.mapper.ArrivalFlightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArrivalFlightRepositoryImpl implements ArrivalFlightRepository {

    @Autowired
    private ArrivalCrudRepository arrivalCrudRepository;

    @Autowired
    private ArrivalFlightMapper arrivalFlightMapper;

    @Override
    public Optional<ArrivalFlight> getArrivalById(int arrivalId) {
        return arrivalCrudRepository.findById(arrivalId)
                .map(arrivalEntity -> arrivalFlightMapper.toArrivalFlight(arrivalEntity));
    }

    @Override
    public List<ArrivalFlight> getAllArrivalFlights() {
        return null;
    }

    @Override
    public ArrivalFlight saveArrival(ArrivalFlight arrivalFlight) {
        ArrivalFlightEntity arrivalFlightEntity = arrivalFlightMapper.toArrivalFlightEntity(arrivalFlight);
        return arrivalFlightMapper.toArrivalFlight(arrivalCrudRepository.save(arrivalFlightEntity));
    }
}
