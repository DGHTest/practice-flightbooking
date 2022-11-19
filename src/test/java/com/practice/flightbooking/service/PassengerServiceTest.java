package com.practice.flightbooking.service;

import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.domain.repository.PassengerRepository;
import com.practice.flightbooking.domain.service.PassengerService;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import com.practice.flightbooking.persistence.mapper.PassengerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PassengerServiceTest {

    @Autowired
    private PassengerService passengerService;

    @MockBean
    private PassengerRepository passengerRepository;

    private List<Passenger> passengers;

    @BeforeEach
    void setUp() {
        Passenger passenger1 = Passenger.builder()
                .setPassengerId(1)
                .setLastNames("Ramirez Flores")
                .setFirstName("Jose")
                .setBirthDate(LocalDate.of(1970, 5, 8))
                .setEmail("joseramirez1@random.names")
                .setTelephoneNumber("5550657078")
                .setCountry("Mexico")
                .setState("Coahuila")
                .setCity("Torreon")
                .setPassportNumber(6549647689L)
                .setExpirationDate(LocalDate.of(2024, 11, 28))
                .setNationality("MEX")
                .create();

        Passenger passenger2 = Passenger.builder()
                .setPassengerId(2)
                .setLastNames("Hernandez Sanchez")
                .setFirstName("Maria")
                .setBirthDate(LocalDate.of(1999, Month.SEPTEMBER, 25))
                .setEmail("mariahernandez1@random.names")
                .setTelephoneNumber("5546460968")
                .setCountry("Mexico")
                .setState("Nuevo Leon")
                .setCity("Monterrey")
                .setPassportNumber(3453476534L)
                .setExpirationDate(LocalDate.of(2026, Month.NOVEMBER, 12))
                .setNationality("MEX")
                .create();

        Passenger passenger3 = Passenger.builder()
                .setPassengerId(3)
                .setLastNames("Default is my last name")
                .setFirstName("Default")
                .setBirthDate(LocalDate.of(3000, 01, 01))
                .setEmail("default@default.com")
                .setTelephoneNumber("000000000000")
                .setCountry("Default Country")
                .setState("Default State")
                .setCity("Default City")
                .setPassportNumber(0000000000L)
                .setExpirationDate(LocalDate.of(2000, 01, 01))
                .setNationality("DFT")
                .create();

        passengers = Arrays.asList(passenger1, passenger2, passenger3);

    }

    @Test
    @DisplayName("Should return one passenger with the specific id using the repository")
    void getPassengerById() throws Exception {
        Mockito.when(passengerRepository.getPassengerById(2))
                .thenReturn(passengers.get(1));

        Passenger passengerById = passengerService.getPassengerById(2);

        assertEquals(2, passengerById.getPassengerId());
    }

    @Test
    @DisplayName("Should return one passenger with the specific email using the repository")
    void getPassengerByEmail() throws Exception {
        Mockito.when(passengerRepository.getPassengerByEmail("joseramirez1@random.names"))
                .thenReturn(passengers.get(0));

        Passenger passengerByEmail = passengerService.getPassengerByEmail("joseramirez1@random.names");

        assertAll(
                () -> assertEquals(1, passengerByEmail.getPassengerId()),
                () -> assertEquals("joseramirez1@random.names", passengerByEmail.getEmail())
        );
    }

    @Test
    @DisplayName("Should save one passenger and return it with the repository")
    void savePassenger() throws Exception {
        Passenger passenger = Passenger.builder()
                .setPassengerId(32)
                .setLastNames("Hernandez")
                .setFirstName("Serrano")
                .setBirthDate(LocalDate.of(1972, Month.JULY, 25))
                .setEmail("serrano@random.names")
                .setTelephoneNumber("5546223968")
                .setCountry("Mexico")
                .setState("Nuevo Leon")
                .setCity("Monterrey")
                .setPassportNumber(34511788534L)
                .setExpirationDate(LocalDate.of(2023, Month.AUGUST, 12))
                .setNationality("MEX")
                .create();

        Mockito.when(passengerRepository.savePassenger(ArgumentMatchers.any())).thenReturn(passenger);

        Passenger savePassenger = passengerService.savePassenger(passenger);

        assertAll(
                () -> assertEquals(passenger.getPassengerId(), savePassenger.getPassengerId()),
                () -> assertEquals(passenger.getLastNames(), savePassenger.getLastNames()),
                () -> assertEquals(passenger.getFirstName(), savePassenger.getFirstName()),
                () -> assertEquals(passenger.getBirthDate(), savePassenger.getBirthDate()),
                () -> assertEquals(passenger.getEmail(), savePassenger.getEmail()),
                () -> assertEquals(passenger.getTelephoneNumber(), savePassenger.getTelephoneNumber()),
                () -> assertEquals(passenger.getCountry(), savePassenger.getCountry()),
                () -> assertEquals(passenger.getState(), savePassenger.getState()),
                () -> assertEquals(passenger.getCity(), savePassenger.getCity()),
                () -> assertEquals(passenger.getPassportNumber(), savePassenger.getPassportNumber()),
                () -> assertEquals(passenger.getExpirationDate(), savePassenger.getExpirationDate()),
                () -> assertEquals(passenger.getNationality(), savePassenger.getNationality())
        );
    }

    @Test
    @DisplayName("Should update the status wanted of one passengerEntity with the specific id using the repository")
    void updatePassengerStatusById() throws Exception {
        PassengerService passengerServiceMock = Mockito.mock(PassengerService.class);

        Mockito.when(passengerService.updatePassengerStatusById(true, 4))
                .thenThrow(Exception.class);

        Mockito.doReturn(true).when(passengerServiceMock)
                .updatePassengerStatusById(false, 8);

        assertAll(
                () -> assertFalse(passengerService.updatePassengerStatusById(true, 4)),
                () -> assertTrue(passengerService.updatePassengerStatusById(false, 8))
        );
    }

    @Test
    @DisplayName("Should delete one passengerEntity with the specific id using the repository")
    void deletePassenger() throws Exception {
        PassengerService passengerServiceMock = Mockito.mock(PassengerService.class);

        Mockito.when(passengerService.deletePassenger(4))
                .thenThrow(Exception.class);

        Mockito.doReturn(true).when(passengerServiceMock)
                .deletePassenger(8);

        assertAll(
                () -> assertFalse(passengerService.deletePassenger(4)),
                () -> assertTrue(passengerService.deletePassenger(8))
        );
    }
}