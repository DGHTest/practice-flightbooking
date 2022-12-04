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
    public Optional<Departure> getDepartureById(int departureId) {
        return departureCrudRepository.findById(departureId)
                .map(departure -> departureMapper.toDeparture(departure));
    }

    @Override
    public Optional<List<Departure>> getByIdAirport(int airportId) {
        return departureCrudRepository.findByIdAirportAndStatus(airportId, true)
                .map(departures -> departureMapper.toDepartures(departures));
    }

    @Override
    public Optional<List<Departure>> getByDepartureTime(LocalDateTime departureTime) {
        return departureCrudRepository.findByDepartureTimeAfterAndStatus(departureTime, true)
                .map(departures -> departureMapper.toDepartures(departures));
    }

    @Override
    public Departure saveDeparture(Departure departure) {
        DepartureEntity departureEntity = departureMapper.toDepartureEntity(departure);
        return departureMapper.toDeparture(departureCrudRepository.save(departureEntity));
    }
}
