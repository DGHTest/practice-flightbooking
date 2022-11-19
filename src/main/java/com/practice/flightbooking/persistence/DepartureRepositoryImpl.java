package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.DepartureRepository;
import com.practice.flightbooking.domain.Departure;
import com.practice.flightbooking.persistence.crud.DepartureCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import com.practice.flightbooking.persistence.mapper.DepartureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartureRepositoryImpl implements DepartureRepository {

    @Autowired
    private DepartureCrudRepository departureCrudRepository;

    @Autowired
    private DepartureMapper departureMapper;

    @Override
    public List<Departure> getAllDepartures() {
        List<DepartureEntity> allDepartures = departureCrudRepository.findByStatus(true);
        return departureMapper.toDepartures(allDepartures);
    }

    @Override
    public Departure getDepartureById(int departureId) throws Exception {
        Optional<DepartureEntity> arrivalFlightById = departureCrudRepository.findById(departureId);

        if (arrivalFlightById.isPresent()) {
            return departureMapper.toDeparture(arrivalFlightById.get());
        } else {
            throw new Exception("Departure by id not found");
        }
    }

    @Override
    public List<Departure> getByIdAirport(int airportId) throws Exception {
        Optional<List<DepartureEntity>> departureByIdAirport = departureCrudRepository.findByIdAirportAndStatus(airportId, true);

        if (departureByIdAirport.isPresent()) {
            return departureMapper.toDepartures(departureByIdAirport.get());
        } else {
            throw new Exception("Airport id not found");
        }
    }

    @Override
    public List<Departure> getByDepartureTime(LocalDateTime departureTime) throws Exception {
        Optional<List<DepartureEntity>> departureByArrivalTime = departureCrudRepository.findByDepartureTimeAfterAndStatus(departureTime, true);

        if (departureByArrivalTime.isPresent()) {
            return departureMapper.toDepartures(departureByArrivalTime.get());
        } else {
            throw new Exception("There are no departures after the given departure time");
        }
    }

    @Override
    public Departure saveDeparture(Departure departure) throws Exception {
        DepartureEntity departureEntity = departureMapper.toDepartureEntity(departure);

        if (!departureCrudRepository.existsById(departureEntity.getIdDeparture())) {
            return departureMapper.toDeparture(departureCrudRepository.save(departureEntity));
        } else {
            throw new Exception("Id already exist");
        }
    }
}
