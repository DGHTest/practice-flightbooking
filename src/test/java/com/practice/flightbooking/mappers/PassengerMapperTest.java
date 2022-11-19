package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import com.practice.flightbooking.persistence.mapper.PassengerMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class PassengerMapperTest {

    private PassengerMapper passengerMapper = Mappers.getMapper(PassengerMapper.class);

    @Test
    @DisplayName("Should transform the entity values to service values with the data mapper")
    public void shouldTransformTheEntityValuesToServiceValuesWithTheDataMapper() {
        PassengerEntity entity = PassengerEntity.builder()
                .setIdPassenger(23)
                .setLastNames("Juan Zurita")
                .setFirstName("Jose")
                .setBirthDate(LocalDate.of(1970, Month.OCTOBER, 12))
                .setEmail("juan3452354@gmail.com")
                .setTelephoneNumber("5543435412")
                .setCountry("México")
                .setState("Hidalgo")
                .setCity("Suarez")
                .setPassportNumber(3290435478L)
                .setExpirationDate(LocalDate.of(2030, Month.MARCH, 31))
                .setNationality("MEX")
                .setStatus(true)
                .create();

        Passenger passenger = passengerMapper.toPassenger(entity);

        assertAll(
                () -> assertNotNull(passenger),
                () -> assertEquals(entity.getIdPassenger(), passenger.getPassengerId()),
                () -> assertEquals(entity.getLastNames(), passenger.getLastNames()),
                () -> assertEquals(entity.getFirstName(), passenger.getFirstName()),
                () -> assertEquals(entity.getBirthDate(), passenger.getBirthDate()),
                () -> assertEquals(entity.getEmail(), passenger.getEmail()),
                () -> assertEquals(entity.getTelephoneNumber(), passenger.getTelephoneNumber()),
                () -> assertEquals(entity.getCountry(), passenger.getCountry()),
                () -> assertEquals(entity.getState(), passenger.getState()),
                () -> assertEquals(entity.getCity(), passenger.getCity()),
                () -> assertEquals(entity.getPassportNumber(), passenger.getPassportNumber()),
                () -> assertEquals(entity.getExpirationDate(), passenger.getExpirationDate()),
                () -> assertEquals(entity.getNationality(), passenger.getNationality())
        );
    }

    @Test
    @DisplayName("Should transform the service information to entity information")
    public void testForServiceToEntity() {
        Passenger service = Passenger.builder()
                .setPassengerId(23)
                .setLastNames("Juan Zurita")
                .setFirstName("Jose")
                .setBirthDate(LocalDate.of(1970, Month.OCTOBER, 12))
                .setEmail("juan3452354@gmail.com")
                .setTelephoneNumber("5543435412")
                .setCountry("México")
                .setState("Hidalgo")
                .setCity("Suarez")
                .setPassportNumber(3290435478L)
                .setExpirationDate(LocalDate.of(2030, Month.MARCH, 31))
                .setNationality("MEX")
                .create();

        PassengerEntity passengerEntity = passengerMapper.toPassengerEntity(service);

        assertAll(
                () -> assertNotNull(service),
                () -> assertEquals(service.getPassengerId(), passengerEntity.getIdPassenger()),
                () -> assertEquals(service.getLastNames(), passengerEntity.getLastNames()),
                () -> assertEquals(service.getFirstName(), passengerEntity.getFirstName()),
                () -> assertEquals(service.getBirthDate(), passengerEntity.getBirthDate()),
                () -> assertEquals(service.getEmail(), passengerEntity.getEmail()),
                () -> assertEquals(service.getTelephoneNumber(), passengerEntity.getTelephoneNumber()),
                () -> assertEquals(service.getCountry(), passengerEntity.getCountry()),
                () -> assertEquals(service.getState(), passengerEntity.getState()),
                () -> assertEquals(service.getCity(), passengerEntity.getCity()),
                () -> assertEquals(service.getPassportNumber(), passengerEntity.getPassportNumber()),
                () -> assertEquals(service.getExpirationDate(), passengerEntity.getExpirationDate()),
                () -> assertEquals(service.getNationality(), passengerEntity.getNationality()),
                () -> Assertions.assertThat(passengerEntity.getStatus()).isEqualTo(true)
        );
    }

}