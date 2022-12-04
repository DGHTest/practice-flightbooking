package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.ArrivalFlightRepository;
import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.persistence.crud.ArrivalCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.mapper.ArrivalFlightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ArrivalFlightRepositoryImpl implements ArrivalFlightRepository {

    @Autowired
    private ArrivalCrudRepository arrivalCrudRepository;

    @Autowired
    private ArrivalFlightMapper arrivalFlightMapper;

    @Override
    public List<ArrivalFlight> getAllArrivalFlights() {
        List<ArrivalFlightEntity> allArrivalFlights = arrivalCrudRepository.findByStatus(true);
        return arrivalFlightMapper.toArrivalFlights(allArrivalFlights);
    }

    @Override
    public Optional<ArrivalFlight> getArrivalById(int arrivalId) {
        return arrivalCrudRepository.findById(arrivalId)
                .map(arrival -> arrivalFlightMapper.toArrivalFlight(arrival));
    }

    @Override
    public Optional<List<ArrivalFlight>> getByIdAirport(int airportId) {
        return arrivalCrudRepository.findByIdAirportAndStatus(airportId, true)
                .map(arrivals -> arrivalFlightMapper.toArrivalFlights(arrivals));
    }

    @Override
    public Optional<List<ArrivalFlight>> getByArrivalTime(LocalDateTime arrivalTime) {
        return arrivalCrudRepository.findByArrivalTimeAfterAndStatus(arrivalTime, true)
                .map(arrivals -> arrivalFlightMapper.toArrivalFlights(arrivals));
    }

    @Override
    public ArrivalFlight saveArrival(ArrivalFlight arrivalFlight) {
        ArrivalFlightEntity arrivalFlightEntity = arrivalFlightMapper.toArrivalFlightEntity(arrivalFlight);
        return arrivalFlightMapper.toArrivalFlight(arrivalCrudRepository.save(arrivalFlightEntity));
    }
}
