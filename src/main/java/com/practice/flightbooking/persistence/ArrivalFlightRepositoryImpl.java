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
    public ArrivalFlight getArrivalById(int arrivalId) throws Exception {
        Optional<ArrivalFlightEntity> arrivalFlightById = arrivalCrudRepository.findById(arrivalId);

        if (arrivalFlightById.isPresent()) {
            return arrivalFlightMapper.toArrivalFlight(arrivalFlightById.get());
        } else {
            throw new Exception("Arrival flight by id not found");
        }
    }

    @Override
    public List<ArrivalFlight> getByIdAirport(int airportId) throws Exception {
        Optional<List<ArrivalFlightEntity>> arrivalFlightByIdAirport = arrivalCrudRepository.findByIdAirportAndStatus(airportId, true);

        if (arrivalFlightByIdAirport.isPresent()) {
            return arrivalFlightMapper.toArrivalFlights(arrivalFlightByIdAirport.get());
        } else {
            throw new Exception("Airport id not found");
        }
    }

    @Override
    public List<ArrivalFlight> getByArrivalTime(LocalDateTime arrivalTime) throws Exception {
        Optional<List<ArrivalFlightEntity>> arrivalFlightByArrivalTime = arrivalCrudRepository.findByArrivalTimeAfterAndStatus(arrivalTime, true);

        if (arrivalFlightByArrivalTime.isPresent()) {
            return arrivalFlightMapper.toArrivalFlights(arrivalFlightByArrivalTime.get());
        } else {
            throw new Exception("There are no arrival flights after the given arrival time");
        }
    }

    @Override
    public ArrivalFlight saveArrival(ArrivalFlight arrivalFlight) throws Exception {
        ArrivalFlightEntity arrivalFlightEntity = arrivalFlightMapper.toArrivalFlightEntity(arrivalFlight);

        if (!arrivalCrudRepository.existsById(arrivalFlightEntity.getIdArrivalFlight())) {
            return arrivalFlightMapper.toArrivalFlight(arrivalCrudRepository.save(arrivalFlightEntity));
        } else {
            throw new Exception("Id already exist");
        }
    }
}
