package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.service.Airport;
import com.practice.flightbooking.domain.service.Departure;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import com.practice.flightbooking.persistence.mapper.DepartureMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DepartureMapperTest {

    private DepartureEntity entity;
    @Autowired
    private DepartureMapper departureMapper = Mappers.getMapper(DepartureMapper.class);


    @Test
    @DisplayName("Should transform the departureEntity information to departureService information")
    public void testForEntityToService() {

        AirportEntity airportEntity = new AirportEntity.Builder()
                .setCity("Juarez")
                .setCountry("Mexico")
                .setState("Coahuila")
                .setIata("IAD")
                .create();

        entity = new DepartureEntity.Builder()
                .setIdAirport(9)
                .setAirportEntity(airportEntity)
                .create();

        Departure departure = departureMapper.toDeparture(entity);

        assertEquals(entity.getIdAirport(), departure.getAirportId());
        assertEquals(entity.getAirportEntity().getCity(), departure.getAirport().getCity());

    }

    @Test
    @DisplayName("Should transform the service information to entity information")
    public void testForServiceToEntity() {
        Departure service = new Departure();
        service.setDepartureId(8);
        service.setAirportId(2);

        Airport airport = new Airport();
        airport.setCity("Neza");
        airport.setCountry("Mexico");
        airport.setState("Mexico");
        airport.setIata("YRT");
        service.setAirport(airport);


        DepartureEntity departureEntity = departureMapper.toDepartureEntity(service);

        assertEquals(service.getAirportId(), departureEntity.getIdAirport());
        assertEquals(service.getAirport().getCity(), departureEntity.getAirportEntity().getCity());
    }

}