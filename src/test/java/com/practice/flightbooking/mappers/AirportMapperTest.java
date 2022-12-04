package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import com.practice.flightbooking.persistence.mapper.AirportMapper;
import com.practice.flightbooking.persistence.mapper.ArrivalFlightMapper;
import com.practice.flightbooking.persistence.mapper.DepartureMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("dev")
public class AirportMapperTest {

    @Autowired
    private AirportMapper airportMapper;

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
