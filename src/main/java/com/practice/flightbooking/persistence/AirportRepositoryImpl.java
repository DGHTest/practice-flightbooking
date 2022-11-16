package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.AirportRepository;
import com.practice.flightbooking.domain.service.Airport;
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
    public Airport getById(int id) throws Exception {
        Optional<AirportEntity> airportById = airportCrudRepository.findById(id);

        if (airportById.isPresent()) {
            return airportMapper.toAirport(airportById.get());
        } else {
            throw new Exception("Airports by id not found");
        }
    }

    @Override
    public List<Airport> getByCountry(String country) throws Exception {
        Optional<List<AirportEntity>> airportByCountry = airportCrudRepository.findByCountry(country);

        if (airportByCountry.isPresent()) {
            return airportMapper.toAirports(airportByCountry.get());
        } else {
            throw new Exception("Airports by country not found");
        }
    }

    @Override
    public List<Airport> getByState(String state) throws Exception {
        Optional<List<AirportEntity>> airportsByState = airportCrudRepository.findByState(state);

        if (airportsByState.isPresent()) {
            return airportMapper.toAirports(airportsByState.get());
        } else {
            throw new Exception("Airports by state not found");
        }
    }

    @Override
    public List<Airport> getByCity(String city) throws Exception {
        Optional<List<AirportEntity>> airportsByCity = airportCrudRepository.findByCity(city);

        if (airportsByCity.isPresent()) {
            return airportMapper.toAirports(airportsByCity.get());
        } else {
            throw new Exception("Airports by city not found");
        }

    }
}
