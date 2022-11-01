package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.service.Passenger;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import com.practice.flightbooking.persistence.mapper.PassengerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class PassengerMapperTest {

    private PassengerEntity entity;
    private PassengerMapper passengerMapper = Mappers.getMapper(PassengerMapper.class);

    @BeforeEach
    void setUP() {
        entity = new PassengerEntity.Builder()
            .setCity("Juarez")
            .setEmail("gael@dark.com")
            .create();
    }

    @Test
    @DisplayName("Should transform the entity values to service values with the data mapper")
    public void shouldTransformTheEntityValuesToServiceValuesWithTheDataMapper() {

        Passenger passenger = passengerMapper.toPassenger(entity);
        assertNotNull(passenger.getPassengerId());
        assertEquals(entity.getCity(), passenger.getCity());
    }

    @Test
    @DisplayName("Should transform the email entity to service")
    public void shouldTransformEmailToService() {
        Passenger passenger = passengerMapper.toPassenger(entity);
        assertEquals(entity.getEmail(), passenger.getEmail());
    }

    @Test
    @DisplayName("Should transform the passengersService information to passengersEntity information")
    public void testForServiceToEntity() {
        Passenger service = new Passenger();
        service.setPassengerId(9);
        service.setCity("Juarez");
        service.setEmail("gael@dark.com");
        service.setPassportNumber(1234675432);

        PassengerEntity passengerEntity = passengerMapper.toPassengerEntity(service);

        assertEquals(service.getCity(), passengerEntity.getCity());
        assertEquals(service.getPassportNumber(), passengerEntity.getPassportNumber());
    }

}