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
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
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

        Assertions.assertAll("Test with a persist entity",
                () -> assertThat(3).isEqualTo(findId.getIdPassenger()),
                () -> Assertions.assertEquals("Juarez Hidalgo", findId.getLastNames()),
                () -> Assertions.assertEquals("Manuel", findId.getFirstName()),
                () -> Assertions.assertEquals(LocalDate.of(1990, Month.APRIL, 23), findId.getBirthDate()),
                () -> Assertions.assertEquals("random@gmail.random.com", findId.getEmail()),
                () -> Assertions.assertEquals("5576905634", findId.getTelephoneNumber()),
                () -> Assertions.assertEquals("Mexico", findId.getCountry()),
                () -> Assertions.assertEquals("Colima", findId.getState()),
                () -> Assertions.assertEquals("Soloma", findId.getCity()),
                () -> Assertions.assertEquals(34525478544L, findId.getPassportNumber()),
                () -> Assertions.assertEquals(LocalDate.of(2024, Month.SEPTEMBER, 21), findId.getExpirationDate()),
                () -> Assertions.assertEquals("MEX", findId.getNationality())
        );

        PassengerEntity findId2 = crudRepository.findById(2).get();

        Assertions.assertEquals(2, findId2.getIdPassenger());
        Assertions.assertEquals("Nuevo Leon", findId2.getState());

        Assertions.assertAll("Test with the data.sql",
                () -> assertThat(2).isEqualTo(findId2.getIdPassenger()),
                () -> Assertions.assertEquals("Hernandez Sanchez", findId2.getLastNames()),
                () -> Assertions.assertEquals("Maria", findId2.getFirstName()),
                () -> Assertions.assertEquals(LocalDate.of(1999, Month.SEPTEMBER, 25), findId2.getBirthDate()),
                () -> Assertions.assertEquals("mariahernandez1@random.names", findId2.getEmail()),
                () -> Assertions.assertEquals("5546460968", findId2.getTelephoneNumber()),
                () -> Assertions.assertEquals("Mexico", findId2.getCountry()),
                () -> Assertions.assertEquals("Nuevo Leon", findId2.getState()),
                () -> Assertions.assertEquals("Monterrey", findId2.getCity()),
                () -> Assertions.assertEquals(3453476534L, findId2.getPassportNumber()),
                () -> Assertions.assertEquals(LocalDate.of(2026, Month.MARCH, 12), findId2.getExpirationDate()),
                () -> Assertions.assertEquals("MEX", findId2.getNationality()),
                () -> Assertions.assertEquals(true, findId2.getActive())
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

        Assertions.assertAll(
                () -> assertThat(passengerSaved.getIdPassenger()).isEqualTo(passengerEntity.getIdPassenger()),
                () -> Assertions.assertEquals(passengerEntity.getLastNames(), passengerSaved.getLastNames()),
                () -> Assertions.assertEquals(passengerEntity.getFirstName(), passengerSaved.getFirstName()),
                () -> Assertions.assertEquals(passengerEntity.getBirthDate(), passengerSaved.getBirthDate()),
                () -> Assertions.assertEquals(passengerEntity.getEmail(), passengerSaved.getEmail()),
                () -> Assertions.assertEquals(passengerEntity.getTelephoneNumber(), passengerSaved.getTelephoneNumber()),
                () -> Assertions.assertEquals(passengerEntity.getCountry(), passengerSaved.getCountry()),
                () -> Assertions.assertEquals(passengerEntity.getState(), passengerSaved.getState()),
                () -> Assertions.assertEquals(passengerEntity.getCity(), passengerSaved.getCity()),
                () -> Assertions.assertEquals(passengerEntity.getPassportNumber(), passengerSaved.getPassportNumber()),
                () -> Assertions.assertEquals(passengerEntity.getExpirationDate(), passengerSaved.getExpirationDate()),
                () -> Assertions.assertEquals(passengerEntity.getNationality(), passengerSaved.getNationality()),
                () -> Assertions.assertEquals(passengerEntity.getTravelsEntities(), passengerSaved.getTravelsEntities())
        );
    }


    @Test
    @DisplayName("Should return a passenger by his email")
    public void findByEmail() {
        PassengerEntity passengerByEmail = crudRepository.findByEmail("mariahernandez1@random.names").get();

        Assertions.assertAll(
                () -> assertThat(2).isEqualTo(passengerByEmail.getIdPassenger()),
                () -> Assertions.assertEquals("Hernandez Sanchez", passengerByEmail.getLastNames()),
                () -> Assertions.assertEquals("Maria", passengerByEmail.getFirstName()),
                () -> Assertions.assertEquals(LocalDate.of(1999, Month.SEPTEMBER, 25), passengerByEmail.getBirthDate()),
                () -> Assertions.assertEquals("mariahernandez1@random.names", passengerByEmail.getEmail()),
                () -> Assertions.assertEquals("5546460968", passengerByEmail.getTelephoneNumber()),
                () -> Assertions.assertEquals("Mexico", passengerByEmail.getCountry()),
                () -> Assertions.assertEquals("Nuevo Leon", passengerByEmail.getState()),
                () -> Assertions.assertEquals("Monterrey", passengerByEmail.getCity()),
                () -> Assertions.assertEquals(3453476534L, passengerByEmail.getPassportNumber()),
                () -> Assertions.assertEquals(LocalDate.of(2026, Month.MARCH, 12), passengerByEmail.getExpirationDate()),
                () -> Assertions.assertEquals("MEX", passengerByEmail.getNationality()),
                () -> Assertions.assertEquals(true, passengerByEmail.getActive())
        );

        Assertions.assertFalse(crudRepository.findByEmail("gdsgr@gsd..com").isPresent());
    }
}