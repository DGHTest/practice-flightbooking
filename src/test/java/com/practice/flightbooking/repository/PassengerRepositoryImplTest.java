package com.practice.flightbooking.repository;

import com.practice.flightbooking.domain.repository.PassengerRepository;
import com.practice.flightbooking.domain.service.Passenger;
import com.practice.flightbooking.persistence.crud.PassengerCrudRepository;
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
class PassengerRepositoryImplTest {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerMapper passengerMapper;

    @MockBean
    private PassengerCrudRepository passengerCrudRepository;

    private Optional<List<PassengerEntity>> optionalPassengers;

    @BeforeEach
    void setUp() {
        PassengerEntity passengerEntity1 = PassengerEntity.builder()
                .setIdPassenger(1)
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
                .setStatus(true)
                .create();

        PassengerEntity passengerEntity2 = PassengerEntity.builder()
                .setIdPassenger(2)
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
                .setStatus(true)
                .create();

        PassengerEntity passengerEntity3 = PassengerEntity.builder()
                .setIdPassenger(3)
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
                .setStatus(false)
                .create();

        optionalPassengers = Optional.of(Arrays.asList(passengerEntity1, passengerEntity2, passengerEntity3));
    }

    @Test
    @DisplayName("Should return one passengerEntity with the specific id and the mapper should transform to passenger or throw an error if the id is not found")
    void getPassengerById() throws Exception {
        Mockito.when(passengerCrudRepository.findById(2))
                .thenReturn(Optional.of(optionalPassengers.get().get(1)));

        Passenger passengerById = passengerRepository.getPassengerById(2);

        Exception exception1 = assertThrows(Exception.class, () -> passengerRepository.getPassengerById(6));
        Exception exception2 = assertThrows(Exception.class, () -> Integer.parseInt("id "));

        String expectedMessage = "Passenger id not found";

        assertAll(
                () -> assertEquals(expectedMessage, exception1.getMessage()),
                () -> assertNotEquals(expectedMessage, exception2.getMessage()),
                () -> assertEquals(2, passengerById.getPassengerId())
        );
    }

    @Test
    @DisplayName("Should return one passengerEntity with the specific email and the mapper should transform to passenger or throw an error if the email is not found")
    void getPassengerByEmail() throws Exception {
        Mockito.when(passengerCrudRepository.findByEmail("joseramirez1@random.names"))
                .thenReturn(Optional.of(optionalPassengers.get().get(0)));

        Passenger passengerById = passengerRepository.getPassengerByEmail("joseramirez1@random.names");

        Exception exception1 = assertThrows(Exception.class, () -> passengerRepository.getPassengerByEmail("@random.names"));
        Exception exception2 = assertThrows(Exception.class, () -> Integer.parseInt("id "));

        String expectedMessage = "Email not found";

        assertAll(
                () -> assertEquals(expectedMessage, exception1.getMessage()),
                () -> assertNotEquals(expectedMessage, exception2.getMessage()),
                () -> assertEquals(1, passengerById.getPassengerId()),
                () -> assertEquals("joseramirez1@random.names", passengerById.getEmail())
        );
    }

    @Test
    @DisplayName("Should save one passengerEntity and return it with the mapper to passenger or throw an error if the id already exist")
    void savePassenger() throws Exception {
        PassengerEntity passengerEntity = PassengerEntity.builder()
                .setIdPassenger(32)
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
                .setStatus(true)
                .create();

        Mockito.when(passengerCrudRepository.save(ArgumentMatchers.any())).thenReturn(passengerEntity);

        Passenger savePassenger = passengerRepository.savePassenger(Mappers.getMapper(PassengerMapper.class).toPassenger(passengerEntity));

        Exception exception = assertThrows(Exception.class, () -> Integer.parseInt("id "));

        String expectedMessage = "Id already exist";

        assertAll(
                () -> assertNotEquals(expectedMessage, exception.getMessage()),
                () -> assertEquals(passengerEntity.getIdPassenger(), savePassenger.getPassengerId()),
                () -> assertEquals(passengerEntity.getLastNames(), savePassenger.getLastNames()),
                () -> assertEquals(passengerEntity.getFirstName(), savePassenger.getFirstName()),
                () -> assertEquals(passengerEntity.getBirthDate(), savePassenger.getBirthDate()),
                () -> assertEquals(passengerEntity.getEmail(), savePassenger.getEmail()),
                () -> assertEquals(passengerEntity.getTelephoneNumber(), savePassenger.getTelephoneNumber()),
                () -> assertEquals(passengerEntity.getCountry(), savePassenger.getCountry()),
                () -> assertEquals(passengerEntity.getState(), savePassenger.getState()),
                () -> assertEquals(passengerEntity.getCity(), savePassenger.getCity()),
                () -> assertEquals(passengerEntity.getPassportNumber(), savePassenger.getPassportNumber()),
                () -> assertEquals(passengerEntity.getExpirationDate(), savePassenger.getExpirationDate()),
                () -> assertEquals(passengerEntity.getNationality(), savePassenger.getNationality())
        );
    }

    @Test
    @DisplayName("Should update the status wanted of one passengerEntity with the specific id or throw an error if the id is not found")
    void updatePassengerStatusById() throws Exception {
        PassengerRepository passengerRepositoryMock = Mockito.mock(PassengerRepository.class);
        Mockito.doNothing().when(passengerRepositoryMock).updatePassengerStatusById(Mockito.isA(Boolean.class), Mockito.isA(Integer.class));

        passengerRepositoryMock.updatePassengerStatusById(true, 3);
        passengerRepositoryMock.updatePassengerStatusById(false, 3);

        Exception exception1 = assertThrows(Exception.class, () -> passengerRepository.updatePassengerStatusById(true, 6));
        Exception exception2 = assertThrows(Exception.class, () -> Integer.parseInt("id "));

        String expectedMessage = "Id not found";

        assertAll(
                () -> assertEquals(expectedMessage, exception1.getMessage()),
                () -> assertNotEquals(expectedMessage, exception2.getMessage()),
                () -> Mockito.verify(passengerRepositoryMock, Mockito.times(1)).updatePassengerStatusById(true, 3),
                () -> Mockito.verify(passengerRepositoryMock, Mockito.times(1)).updatePassengerStatusById(false, 3)
        );
    }

    @Test
    @DisplayName("Should return one passengerEntity with the specific id and the mapper should transform to passenger or throw an error if the id is not found")
    void deletePassenger() throws Exception {
        PassengerRepository passengerRepositoryMock = Mockito.mock(PassengerRepository.class);
        Mockito.doNothing().when(passengerRepositoryMock).deletePassenger(Mockito.isA(Integer.class));

        passengerRepositoryMock.deletePassenger(1);

        Exception exception1 = assertThrows(Exception.class, () -> passengerRepository.deletePassenger(6));
        Exception exception2 = assertThrows(Exception.class, () -> Integer.parseInt("id "));

        String expectedMessage = "Id not found";

        assertAll(
                () -> assertEquals(expectedMessage, exception1.getMessage()),
                () -> assertNotEquals(expectedMessage, exception2.getMessage()),
                () -> Mockito.verify(passengerRepositoryMock, Mockito.times(1)).deletePassenger(1)
        );
    }
}