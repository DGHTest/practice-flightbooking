package com.practice.flightbooking.crud;

import com.practice.flightbooking.persistence.crud.AirportCrudRepository;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("dev")
@Sql("/db/flightbooking_data.sql")
class AirportCrudRepositoryTest {

    @Autowired
    private AirportCrudRepository crudRepository;

    @Test
    @DisplayName("Should return a airport by his id in the data.sql")
    public void findById() {
        AirportEntity airportEntity = crudRepository.findById(3).get();

        assertAll(
                ()-> assertEquals(3, airportEntity.getIdAirport()),
                ()-> assertEquals("Mexico", airportEntity.getCountry()),
                ()-> assertEquals("Nuevo Leon", airportEntity.getState()),
                ()-> assertEquals("Monterrey", airportEntity.getCity()),
                ()-> assertEquals("MTY", airportEntity.getIata())
        );
    }

    @Test
    @DisplayName("Should return all airports by his country in the data.sql")
    public void findByCountry() {
        List<AirportEntity> airportEntityUnitedStates = crudRepository.findByCountry("United States").get();

        assertAll(
                () -> Assertions.assertThat(airportEntityUnitedStates.size()).isEqualTo(2),
                () -> assertEquals(1, airportEntityUnitedStates.get(0).getIdAirport()),
                () -> assertEquals("United States", airportEntityUnitedStates.get(0).getCountry()),
                () -> assertEquals(2, airportEntityUnitedStates.get(1).getIdAirport()),
                () -> assertEquals("United States", airportEntityUnitedStates.get(1).getCountry())
        );

        List<AirportEntity> airportEntityMexico = crudRepository.findByCountry("Mexico").get();

        assertAll(
                () -> Assertions.assertThat(airportEntityMexico.size()).isEqualTo(2),
                () -> assertEquals(3, airportEntityMexico.get(0).getIdAirport()),
                () -> assertEquals("Mexico", airportEntityMexico.get(0).getCountry()),
                () -> assertEquals(4, airportEntityMexico.get(1).getIdAirport()),
                () -> assertEquals("Mexico", airportEntityMexico.get(1).getCountry())
        );

        List<AirportEntity> airportEntityArgentina = crudRepository.findByCountry("Argentina").get();

        assertAll(
                () -> Assertions.assertThat(airportEntityArgentina.size()).isEqualTo(1),
                () -> assertEquals(5, airportEntityArgentina.get(0).getIdAirport()),
                () -> assertEquals("Argentina", airportEntityArgentina.get(0).getCountry())
        );

    }

    @Test
    @DisplayName("Should return a airport by his state in the data.sql")
    public void findByState() {
        AirportEntity airportEntityGeorgia = crudRepository.findByState("Georgia").get().get(0);

        assertAll(
                () -> assertEquals(1, airportEntityGeorgia.getIdAirport()),
                () -> assertEquals("Georgia", airportEntityGeorgia.getState())
        );

        AirportEntity airportEntityTexas = crudRepository.findByState("Texas").get().get(0);

        assertAll(
                () -> assertEquals(2, airportEntityTexas.getIdAirport()),
                () -> assertEquals("Texas", airportEntityTexas.getState())
        );

        AirportEntity airportEntityNuevoLeon = crudRepository.findByState("Nuevo Leon").get().get(0);

        assertAll(
                () -> assertEquals(3, airportEntityNuevoLeon.getIdAirport()),
                () -> assertEquals("Nuevo Leon", airportEntityNuevoLeon.getState())
        );

        AirportEntity airportEntityCoahuila = crudRepository.findByState("Coahuila").get().get(0);

        assertAll(
                () -> assertEquals(4, airportEntityCoahuila.getIdAirport()),
                () -> assertEquals("Coahuila", airportEntityCoahuila.getState())
        );

        AirportEntity airportEntityChubut = crudRepository.findByState("Chubut").get().get(0);

        assertAll(
                () -> assertEquals(5, airportEntityChubut.getIdAirport()),
                () -> assertEquals("Chubut", airportEntityChubut.getState())
        );
    }

    @Test
    @DisplayName("Should return a airport by his city in the data.sql")
    public void findByCity() {
        AirportEntity airportEntityAtlanta = crudRepository.findByCity("Atlanta").get().get(0);

        assertAll(
                () -> assertEquals(1, airportEntityAtlanta.getIdAirport()),
                () -> assertEquals("Atlanta", airportEntityAtlanta.getCity())
        );

        AirportEntity airportEntityDallasFortWorth = crudRepository.findByCity("Dallas-Fort Worth").get().get(0);

        assertAll(
                () -> assertEquals(2, airportEntityDallasFortWorth.getIdAirport()),
                () -> assertEquals("Dallas-Fort Worth", airportEntityDallasFortWorth.getCity())
        );

        AirportEntity airportEntityMonterrey = crudRepository.findByCity("Monterrey").get().get(0);

        assertAll(
                () -> assertEquals(3, airportEntityMonterrey.getIdAirport()),
                () -> assertEquals("Monterrey", airportEntityMonterrey.getCity())
        );

        AirportEntity airportEntityRamosArizpe = crudRepository.findByCity("Ramos Arizpe").get().get(0);

        assertAll(
                () -> assertEquals(4, airportEntityRamosArizpe.getIdAirport()),
                () -> assertEquals("Ramos Arizpe", airportEntityRamosArizpe.getCity())
        );

        AirportEntity airportEntityAltoRíoSenguer = crudRepository.findByCity("Alto Río Senguer").get().get(0);

        assertAll(
                () -> assertEquals(5, airportEntityAltoRíoSenguer.getIdAirport()),
                () -> assertEquals("Alto Río Senguer", airportEntityAltoRíoSenguer.getCity())
        );
    }
}