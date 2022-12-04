package com.practice.flightbooking.crud;

import com.practice.flightbooking.persistence.crud.PassengersTravelsCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntityPk;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("dev")
@Sql("/db/flightbooking_data.sql")
class PassengersTravelsCrudRepositoryTest {

    @Autowired
    private PassengersTravelsCrudRepository crudRepository;

    private PassengersTravelsEntityPk passengersTravelsPk;

    private PassengersTravelsEntity passengersTravels;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {

        passengersTravelsPk = PassengersTravelsEntityPk.builder()
                .setIdPassenger(2)
                .setIdTravel(1).create();
        passengersTravels =PassengersTravelsEntity.builder().setPassengerTravelsId(passengersTravelsPk).create();

        entityManager.persist(passengersTravels);
        entityManager.flush();
    }


    @Test
    @DisplayName("Should return a PassengersTravelsEntity by his composite id in the data.sql")
    public void findById() {

        PassengersTravelsEntity passengersTravelsEntity = crudRepository.findById(passengersTravelsPk).get();

        assertAll("Test with a persist entity",
                () -> assertNotNull(passengersTravelsEntity),
                () -> assertEquals(passengersTravels.getPassengerTravelsId(), passengersTravelsEntity.getPassengerTravelsId()),
                () -> assertEquals(passengersTravels.getPassengerTravelsId().getIdTravel(), passengersTravelsEntity.getPassengerTravelsId().getIdTravel()),
                () -> assertEquals(passengersTravels.getPassengerTravelsId().getIdPassenger(), passengersTravelsEntity.getPassengerTravelsId().getIdPassenger())
        );

        PassengersTravelsEntityPk dataSQl = PassengersTravelsEntityPk.builder()
                .setIdPassenger(1)
                .setIdTravel(2)
                .create();

        PassengersTravelsEntity passengersTravelsEntity1 = crudRepository.findById(dataSQl).get();

        assertAll("Test with the data.sql",
                () -> assertNotNull(passengersTravelsEntity),
                () -> assertEquals(dataSQl, passengersTravelsEntity1.getPassengerTravelsId()),
                () -> assertEquals(2, passengersTravelsEntity1.getPassengerTravelsId().getIdTravel()),
                () -> assertEquals(1, passengersTravelsEntity1.getPassengerTravelsId().getIdPassenger())
        );
    }

    @Test
    @DisplayName("Should return all PassengersTravelsEntity by a specific idPassenger")
    public void findByPassengerTravelsIdIdPassenger() {
        List<PassengersTravelsEntity> passengersTravels1 = crudRepository.findByPassengerTravelsIdIdPassenger(1).get();

        assertAll("Test with the data.sql",
                () -> assertNotNull(passengersTravels1),
                () -> Assertions.assertThat(passengersTravels1.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(1, 1), passengersTravels1.stream().map(ids -> ids.getPassengerTravelsId().getIdPassenger()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 2), passengersTravels1.stream().map(ids -> ids.getPassengerTravelsId().getIdTravel()).collect(Collectors.toList()))
        );

        List<PassengersTravelsEntity> passengersTravels2 = crudRepository.findByPassengerTravelsIdIdPassenger(2).get();

        assertAll("Test with a persist entity",
                () -> assertNotNull(passengersTravels2),
                () -> Assertions.assertThat(passengersTravels2.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(2, 2), passengersTravels2.stream().map(ids -> ids.getPassengerTravelsId().getIdPassenger()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 3), passengersTravels2.stream().map(ids -> ids.getPassengerTravelsId().getIdTravel()).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should save a PassengersTravelsEntity in the database")
    public void savePassengersTravelsEntity() {

        PassengersTravelsEntityPk passengersTravelsPk = PassengersTravelsEntityPk.builder().setIdPassenger(2).setIdTravel(2).create();

        PassengersTravelsEntity passengersTravelsEntity = PassengersTravelsEntity.builder().setPassengerTravelsId(passengersTravelsPk).create();

        crudRepository.save(passengersTravelsEntity);

        PassengersTravelsEntity passengersTravelsSaved = crudRepository.findById(passengersTravelsPk).get();

        assertAll(
                () -> assertTrue(crudRepository.existsById(passengersTravelsPk)),
                () -> assertNotNull(passengersTravelsSaved),
                () -> assertEquals(passengersTravelsPk, passengersTravelsEntity.getPassengerTravelsId()),
                () -> assertEquals(2, passengersTravelsSaved.getPassengerTravelsId().getIdPassenger()),
                () -> assertEquals(2, passengersTravelsSaved.getPassengerTravelsId().getIdTravel())

        );
    }
}