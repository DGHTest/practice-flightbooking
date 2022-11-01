package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.service.Airport;
import com.practice.flightbooking.domain.service.ArrivalFlight;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.mapper.ArrivalFlightMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ArrivalFlightMapperTest {

    private ArrivalFlightEntity entity;
    private ArrivalFlightMapper arrivalFlightMapper = Mappers.getMapper(ArrivalFlightMapper.class);

    @Test
    @DisplayName("Should transform the entity information to service information")
    public void testForEntityToService() {

        AirportEntity airportEntity = new AirportEntity.Builder()
                .setCity("Juarez")
                .setCountry("Mexico")
                .setState("Coahuila")
                .setIata("IAD")
                .create();

        entity = new ArrivalFlightEntity.Builder()
                .setIdAirport(9)
                .setAirportEntity(airportEntity)
                .create();

        ArrivalFlight arrivalFlight = arrivalFlightMapper.toArrivalFlight(entity);

        Assertions.assertEquals(entity.getIdAirport(), arrivalFlight.getAirportId());

    }

    @Test
    @DisplayName("Should transform the service information to entity information")
    public void testForServiceToEntity() {
        ArrivalFlight service = new ArrivalFlight();
        service.setArrivalFlightId(8);
        service.setAirportId(2);

        Airport airport = new Airport();
        airport.setCity("Neza");
        airport.setCountry("Mexico");
        airport.setState("Mexico");
        airport.setIata("YRT");
        service.setAirport(airport);


        ArrivalFlightEntity arrivalFlightEntity = arrivalFlightMapper.toArrivalFlightEntity(service);

        Assertions.assertEquals(service.getAirportId(), arrivalFlightEntity.getIdAirport());
    }
}