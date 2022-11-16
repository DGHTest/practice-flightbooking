package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.service.Departure;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import com.practice.flightbooking.persistence.mapper.DepartureMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DepartureMapperTest {

    @Autowired
    private DepartureMapper departureMapper = Mappers.getMapper(DepartureMapper.class);

    @Test
    @DisplayName("Should transform the entity information to service information")
    public void testForEntityToService() {
        DepartureEntity entity = new DepartureEntity.Builder()
                .setIdDeparture(34)
                .setIdAirport(9)
                .setDepartureTime(LocalDateTime.of(2023, Month.APRIL, 23, 21, 20, 12))
                .setStatus(true)
                .create();

        Departure departure = departureMapper.toDeparture(entity);

        assertAll(
                () -> assertEquals(entity.getIdDeparture(), departure.getDepartureId()),
                () -> assertEquals(entity.getIdAirport(), departure.getAirportId()),
                () -> assertEquals(entity.getDepartureTime(), departure.getDepartureTime())
        );
    }

    @Test
    @DisplayName("Should transform the service information to entity information")
    public void testForServiceToEntity() {
        Departure service = Departure.builder()
                .setDepartureId(8)
                .setAirportId(2)
                .setDepartureTime(LocalDateTime.of(2023, Month.APRIL, 23, 21, 20, 12))
                .create();

        DepartureEntity departureEntity = departureMapper.toDepartureEntity(service);

        assertAll(
                () -> assertEquals(service.getDepartureId(), departureEntity.getIdDeparture()),
                () -> assertEquals(service.getAirportId(), departureEntity.getIdAirport()),
                () -> assertEquals(service.getDepartureTime(), departureEntity.getDepartureTime()),
                () -> org.assertj.core.api.Assertions.assertThat(departureEntity.getStatus()).isEqualTo(true)
        );
    }

}