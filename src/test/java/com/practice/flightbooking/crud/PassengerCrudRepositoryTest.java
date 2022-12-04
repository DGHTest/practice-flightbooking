package com.practice.flightbooking.crud;

import com.practice.flightbooking.persistence.crud.PassengerCrudRepository;
import com.practice.flightbooking.persistence.crud.PassengersTravelsCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntityPk;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("dev")
@Sql("/db/flightbooking_data.sql")
class PassengerCrudRepositoryTest {

    @Autowired
    private PassengerCrudRepository crudRepository;

    private PassengersTravelsCrudRepository passengersTravelsCrudRepository;

    @Autowired
    private TestEntityManager entityManager;

    private PassengerEntity passenger;

    @BeforeEach
    void setUp() {

        passenger =
                PassengerEntity.builder()
                .setLastNames("Juarez Hidalgo")
                .setFirstName("Manuel")
                .setBirthDate(LocalDate.of(1990, Month.APRIL, 23))
                .setEmail("random@gmail.random.com")
                .setTelephoneNumber("5576905634")
                .setCountry("Mexico")
                .setState("Colima")
                .setCity("Soloma")
                .setPassportNumber(34525478544L)
                .setExpirationDate(LocalDate.of(2024, Month.SEPTEMBER, 21))
                .setNationality("MEX")
                .create();

        entityManager.persist(passenger);
        entityManager.flush();
    }

    @Test
    @DisplayName("Should return a passenger by his id")
    public void findPassengerById() {

        PassengerEntity findId = crudRepository.findById(3).get();

        assertAll("Test with a persist entity",
                () -> assertThat(3).isEqualTo(findId.getIdPassenger()),
                () -> assertEquals("Juarez Hidalgo", findId.getLastNames()),
                () -> assertEquals("Manuel", findId.getFirstName()),
                () -> assertEquals(LocalDate.of(1990, Month.APRIL, 23), findId.getBirthDate()),
                () -> assertEquals("random@gmail.random.com", findId.getEmail()),
                () -> assertEquals("5576905634", findId.getTelephoneNumber()),
                () -> assertEquals("Mexico", findId.getCountry()),
                () -> assertEquals("Colima", findId.getState()),
                () -> assertEquals("Soloma", findId.getCity()),
                () -> assertEquals(34525478544L, findId.getPassportNumber()),
                () -> assertEquals(LocalDate.of(2024, Month.SEPTEMBER, 21), findId.getExpirationDate()),
                () -> assertEquals("MEX", findId.getNationality())
        );

        PassengerEntity findId2 = crudRepository.findById(2).get();

        assertEquals(2, findId2.getIdPassenger());
        assertEquals("Nuevo Leon", findId2.getState());

        assertAll("Test with the data.sql",
                () -> assertThat(2).isEqualTo(findId2.getIdPassenger()),
                () -> assertEquals("Hernandez Sanchez", findId2.getLastNames()),
                () -> assertEquals("Maria", findId2.getFirstName()),
                () -> assertEquals(LocalDate.of(1999, Month.SEPTEMBER, 25), findId2.getBirthDate()),
                () -> assertEquals("mariahernandez1@random.names", findId2.getEmail()),
                () -> assertEquals("5546460968", findId2.getTelephoneNumber()),
                () -> assertEquals("Mexico", findId2.getCountry()),
                () -> assertEquals("Nuevo Leon", findId2.getState()),
                () -> assertEquals("Monterrey", findId2.getCity()),
                () -> assertEquals(3453476534L, findId2.getPassportNumber()),
                () -> assertEquals(LocalDate.of(2026, Month.MARCH, 12), findId2.getExpirationDate()),
                () -> assertEquals("MEX", findId2.getNationality()),
                () -> assertEquals(true, findId2.getStatus())
        );
    }

    @Test
    @DisplayName("Should save a passenger in the database")
    public void savePassenger() {

        PassengersTravelsEntityPk passengersTravelsPk1 = PassengersTravelsEntityPk.builder().setIdTravel(20).create();

        PassengersTravelsEntityPk passengersTravelsPk2 = PassengersTravelsEntityPk.builder().setIdTravel(22).create();

        PassengersTravelsEntity passengersTravels1 = PassengersTravelsEntity.builder().setPassengerTravelsId(passengersTravelsPk1).create();

        PassengersTravelsEntity passengersTravels2 = PassengersTravelsEntity.builder().setPassengerTravelsId(passengersTravelsPk2).create();

        PassengerEntity passengerEntity = new PassengerEntity.Builder()
                .setLastNames("Lopez Mateos")
                .setFirstName("Jose")
                .setBirthDate(LocalDate.of(1980, Month.AUGUST, 27))
                .setEmail("random6@random.com")
                .setTelephoneNumber("5579505634")
                .setCountry("Mexico")
                .setState("Colima")
                .setCity("Leon")
                .setPassportNumber(23525478544L)
                .setExpirationDate(LocalDate.of(2024, Month.NOVEMBER, 07))
                .setNationality("MEX")
                .setTravelsEntities(Arrays.asList(passengersTravels1, passengersTravels2))
                .create();


        crudRepository.save(passengerEntity);

        PassengerEntity passengerSaved = crudRepository.findById(4).get();

        assertAll(
                () -> assertThat(passengerSaved.getIdPassenger()).isEqualTo(passengerEntity.getIdPassenger()),
                () -> assertEquals(passengerEntity.getLastNames(), passengerSaved.getLastNames()),
                () -> assertEquals(passengerEntity.getFirstName(), passengerSaved.getFirstName()),
                () -> assertEquals(passengerEntity.getBirthDate(), passengerSaved.getBirthDate()),
                () -> assertEquals(passengerEntity.getEmail(), passengerSaved.getEmail()),
                () -> assertEquals(passengerEntity.getTelephoneNumber(), passengerSaved.getTelephoneNumber()),
                () -> assertEquals(passengerEntity.getCountry(), passengerSaved.getCountry()),
                () -> assertEquals(passengerEntity.getState(), passengerSaved.getState()),
                () -> assertEquals(passengerEntity.getCity(), passengerSaved.getCity()),
                () -> assertEquals(passengerEntity.getPassportNumber(), passengerSaved.getPassportNumber()),
                () -> assertEquals(passengerEntity.getExpirationDate(), passengerSaved.getExpirationDate()),
                () -> assertEquals(passengerEntity.getNationality(), passengerSaved.getNationality()),
                () -> assertEquals(passengerEntity.getTravelsEntities(), passengerSaved.getTravelsEntities())
        );
    }


    @Test
    @DisplayName("Should return a passenger by his email")
    public void findByEmail() {
        PassengerEntity passengerByEmail = crudRepository.findByEmail("mariahernandez1@random.names").get();

        assertAll(
                () -> assertThat(2).isEqualTo(passengerByEmail.getIdPassenger()),
                () -> assertEquals("Hernandez Sanchez", passengerByEmail.getLastNames()),
                () -> assertEquals("Maria", passengerByEmail.getFirstName()),
                () -> assertEquals(LocalDate.of(1999, Month.SEPTEMBER, 25), passengerByEmail.getBirthDate()),
                () -> assertEquals("mariahernandez1@random.names", passengerByEmail.getEmail()),
                () -> assertEquals("5546460968", passengerByEmail.getTelephoneNumber()),
                () -> assertEquals("Mexico", passengerByEmail.getCountry()),
                () -> assertEquals("Nuevo Leon", passengerByEmail.getState()),
                () -> assertEquals("Monterrey", passengerByEmail.getCity()),
                () -> assertEquals(3453476534L, passengerByEmail.getPassportNumber()),
                () -> assertEquals(LocalDate.of(2026, Month.MARCH, 12), passengerByEmail.getExpirationDate()),
                () -> assertEquals("MEX", passengerByEmail.getNationality()),
                () -> assertEquals(true, passengerByEmail.getStatus())
        );

        assertFalse(crudRepository.findByEmail("gdsgr@gsd..com").isPresent());
    }

    @Test
    @DisplayName("Should update the status of a passenger by his id")
    public void updatePassengerStatus() {
        crudRepository.updatePassengerStatus(false, 1);
        PassengerEntity passenger1 = crudRepository.findById(1).get();

        crudRepository.updatePassengerStatus(false, 2);
        PassengerEntity passenger2 = crudRepository.findById(2).get();


        assertAll(
                () -> assertEquals(false, passenger1.getStatus()),
                () -> assertEquals(1, passenger1.getIdPassenger()),
                () -> assertEquals(false, passenger2.getStatus()),
                () -> assertEquals(2, passenger2.getIdPassenger())

        );
    }

    @Test
    @DisplayName("Should set a specific values in a passenger by his id")
    public void deletePassenger_in_PassengerRepository() {
        PassengerEntity passengerEntity = PassengerEntity.builder()
                .setIdPassenger(1)
                .setLastNames("Default is my last name")
                .setFirstName("Default")
                .setBirthDate(LocalDate.of(3000, 01, 01))
                .setEmail("default1@default.com")
                .setTelephoneNumber("000000000000")
                .setCountry("Default Country")
                .setState("Default State")
                .setCity("Default City")
                .setPassportNumber(0000000000L + 1)
                .setExpirationDate(LocalDate.of(2000, 01, 01))
                .setNationality("DFT")
                .setStatus(true)
                .create();

        PassengerEntity passengerSave = crudRepository.save(passengerEntity);

        assertAll(
                () -> assertEquals(passengerEntity.getLastNames(), passengerSave.getLastNames()),
                () -> assertEquals(passengerEntity.getFirstName(), passengerSave.getFirstName()),
                () -> assertEquals(passengerEntity.getBirthDate(), passengerSave.getBirthDate()),
                () -> assertEquals(passengerEntity.getEmail(), passengerSave.getEmail()),
                () -> assertEquals(passengerEntity.getTelephoneNumber(), passengerSave.getTelephoneNumber()),
                () -> assertEquals(passengerEntity.getCountry(), passengerSave.getCountry()),
                () -> assertEquals(passengerEntity.getState(), passengerSave.getState()),
                () -> assertEquals(passengerEntity.getCity(), passengerSave.getCity()),
                () -> assertEquals(passengerEntity.getPassportNumber(), passengerSave.getPassportNumber()),
                () -> assertEquals(passengerEntity.getExpirationDate(), passengerSave.getExpirationDate()),
                () -> assertEquals(passengerEntity.getNationality(), passengerSave.getNationality()),
                () -> assertEquals(passengerEntity.getStatus(), passengerSave.getStatus())
        );
    }
}