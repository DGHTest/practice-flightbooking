package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.AirportRepository;
import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.persistence.crud.AirportCrudRepository;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import com.practice.flightbooking.persistence.mapper.AirportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AirportRepositoryImpl implements AirportRepository {

    @Autowired
    private AirportCrudRepository airportCrudRepository;

    @Autowired
    private AirportMapper airportMapper;

    @Override
    public Optional<Airport> getById(int id) {
        return airportCrudRepository.findById(id)
                .map(airport -> airportMapper.toAirport(airport));
    }

    @Override
    public Optional<List<Airport>> getByCountry(String country) {
        return airportCrudRepository.findByCountry(country)
                .map(airport -> airportMapper.toAirports(airport));
    }

    @Override
    public Optional<List<Airport>> getByState(String state) {
        return airportCrudRepository.findByState(state)
                .map(airport -> airportMapper.toAirports(airport));
    }

    @Override
    public Optional<List<Airport>> getByCity(String city) {
        return airportCrudRepository.findByCity(city)
                .map(airport -> airportMapper.toAirports(airport));
    }
}
