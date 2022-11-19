package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import com.practice.flightbooking.persistence.mapper.AirportMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportMapperTest {

    private AirportMapper airportMapper = Mappers.getMapper(AirportMapper.class);

    @Test
    @DisplayName("Should transform the entity information to service information")
    public void testForEntityToService() {
        AirportEntity entity = AirportEntity.builder()
                .setIdAirport(435)
                .setName("Hshshrdshsr")
                .setCity("Juarez")
                .setCountry("Mexico")
                .setState("Coahuila")
                .setIata("IAD")
                .setIcao("TRGD")
                .create();

        Airport airport = airportMapper.toAirport(entity);

        assertAll(
                () -> assertEquals(entity.getIdAirport(), airport.getAirportId()),
                () -> assertEquals(entity.getCountry(), airport.getCountry()),
                () -> assertEquals(entity.getState(), airport.getState()),
                () -> assertEquals(entity.getCity(), airport.getCity()),
                () -> assertEquals(entity.getIata(), airport.getIata())
        );
    }

    @Test
    @DisplayName("Should transform the service information to entity information")
    public void testForServiceToEntity() {
        Airport service = Airport.builder()
                .setAirportId(34)
                .setCity("Neza")
                .setCountry("Mexico")
                .setState("Mexico")
                .setIata("YRT")
                .create();

        AirportEntity airportEntity = airportMapper.toAirportEntity(service);

        assertAll(
                () -> assertEquals(service.getAirportId(), airportEntity.getIdAirport()),
                () -> assertEquals(service.getCountry(), airportEntity.getCountry()),
                () -> assertEquals(service.getState(), airportEntity.getState()),
                () -> assertEquals(service.getCity(), airportEntity.getCity()),
                () -> assertEquals(service.getIata(), airportEntity.getIata())
        );
    }
}
