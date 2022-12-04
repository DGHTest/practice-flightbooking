package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import com.practice.flightbooking.persistence.mapper.ArrivalFlightMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("dev")
class ArrivalFlightMapperTest {

    @Autowired
    private ArrivalFlightMapper arrivalFlightMapper;

    @Test
    @DisplayName("Should transform the entity information to service information")
    public void testForEntityToService() {
        ArrivalFlightEntity entity = ArrivalFlightEntity.builder()
                .setIdArrivalFlight(23)
                .setIdAirport(9)
                .setArrivalTime(LocalDateTime.of(2023, Month.APRIL, 23, 21, 20, 12))
                .setStatus(true)
                .create();

        ArrivalFlight arrivalFlight = arrivalFlightMapper.toArrivalFlight(entity);

        assertAll(
                () -> assertEquals(entity.getIdArrivalFlight(), arrivalFlight.getArrivalFlightId()),
                () -> assertEquals(entity.getIdAirport(), arrivalFlight.getAirportId()),
                () -> assertEquals(entity.getArrivalTime(), arrivalFlight.getArrivalTime())
        );



    }

    @Test
    @DisplayName("Should transform the service information to entity information")
    public void testForServiceToEntity() {
        ArrivalFlight service = ArrivalFlight.builder()
                .setArrivalFlightId(8)
                .setAirportId(2)
                .setArrivalTime(LocalDateTime.of(2023, Month.APRIL, 23, 21, 20, 12))
                .create();

        ArrivalFlightEntity arrivalFlightEntity = arrivalFlightMapper.toArrivalFlightEntity(service);

        assertAll(
                () -> assertEquals(service.getArrivalFlightId(), arrivalFlightEntity.getIdArrivalFlight()),
                () -> assertEquals(service.getAirportId(), arrivalFlightEntity.getIdAirport()),
                () -> assertEquals(service.getArrivalTime(), arrivalFlightEntity.getArrivalTime())
        );
    }
}