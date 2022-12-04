package com.practice.flightbooking.repository;

import com.practice.flightbooking.domain.repository.PassengerRepository;
import com.practice.flightbooking.domain.Passenger;
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
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
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
    @DisplayName("Should return one passengerEntity with the specific id and the mapper should transform to passenger")
    void getPassengerById() throws Exception {
        Mockito.when(passengerCrudRepository.findById(2))
                .thenReturn(Optional.of(optionalPassengers.get().get(1)));

        Passenger passengerById = passengerRepository.getPassengerById(2).get();

        assertEquals(2, passengerById.getPassengerId());
    }

    @Test
    @DisplayName("Should return one passengerEntity with the specific email and the mapper should transform to passenger")
    void getPassengerByEmail() throws Exception {
        Mockito.when(passengerCrudRepository.findByEmail("joseramirez1@random.names"))
                .thenReturn(Optional.of(optionalPassengers.get().get(0)));

        Passenger passengerByEmail = passengerRepository.getPassengerByEmail("joseramirez1@random.names").get();

        assertAll(
                () -> assertEquals(1, passengerByEmail.getPassengerId()),
                () -> assertEquals("joseramirez1@random.names", passengerByEmail.getEmail())
        );
    }

    @Test
    @DisplayName("Should save one passengerEntity and return it with the mapper to passenger")
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

        Passenger savePassenger = passengerRepository.savePassenger(passengerMapper.toPassenger(passengerEntity));

        assertAll(
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
    @DisplayName("Should update the status wanted of one passengerEntity with the specific id")
    void updatePassengerStatusById() throws Exception {
        PassengerRepository passengerRepositoryMock = Mockito.mock(PassengerRepository.class);
        Mockito.doNothing().when(passengerRepositoryMock).updatePassengerStatusById(Mockito.isA(Boolean.class), Mockito.isA(Integer.class));

        passengerRepositoryMock.updatePassengerStatusById(true, 3);
        passengerRepositoryMock.updatePassengerStatusById(false, 3);

        assertAll(
                () -> Mockito.verify(passengerRepositoryMock, Mockito.times(1)).updatePassengerStatusById(true, 3),
                () -> Mockito.verify(passengerRepositoryMock, Mockito.times(1)).updatePassengerStatusById(false, 3)
        );
    }

    @Test
    @DisplayName("Should delete one passengerEntity with the specific id")
    void deletePassenger() throws Exception {
        PassengerRepository passengerRepositoryMock = Mockito.mock(PassengerRepository.class);
        Mockito.doNothing().when(passengerRepositoryMock).deletePassenger(Mockito.isA(Integer.class));

        passengerRepositoryMock.deletePassenger(1);

        Mockito.verify(passengerRepositoryMock, Mockito.times(1)).deletePassenger(1);
    }
}