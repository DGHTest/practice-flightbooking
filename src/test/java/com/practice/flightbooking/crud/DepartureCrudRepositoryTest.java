package com.practice.flightbooking.crud;

import com.practice.flightbooking.domain.Departure;
import com.practice.flightbooking.persistence.crud.DepartureCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("dev")
@Sql("/db/flightbooking_data.sql")
class DepartureCrudRepositoryTest {

    @Autowired
    private DepartureCrudRepository crudRepository;

    @Autowired
    private TestEntityManager entityManager;

    private DepartureEntity departure;

    @BeforeEach
    void setUp() {

        departure = DepartureEntity.builder()
                .setIdAirport(3)
                .setDepartureTime(LocalDateTime.of(2022, Month.NOVEMBER, 23, 16, 30, 00))
                .setStatus(true)
                .create();

        entityManager.persist(departure);
        entityManager.flush();
    }

    @Test
    @DisplayName("Should return a arrivalFlight of the data.sql")
    public void findById() {

        DepartureEntity departureEntity = crudRepository.findById(4).get();

        assertAll("Test with a persist entity",
                () -> assertThat(departureEntity.getIdDeparture()).isEqualTo(departure.getIdDeparture()),
                () -> assertThat(departureEntity.getIdAirport()).isEqualTo(departure.getIdAirport()),
                () -> assertThat(departureEntity.getStatus()).isEqualTo(departure.getStatus()),
                () -> assertEquals(departure.getDepartureTime(), departureEntity.getDepartureTime())
        );

        DepartureEntity departureEntity1 = crudRepository.findById(2).get();

        assertAll("Test with the data.sql",
                () -> assertThat(departureEntity1.getIdDeparture()).isEqualTo(2),
                () -> assertThat(departureEntity1.getIdAirport()).isEqualTo(2),
                () -> assertThat(departureEntity1.getStatus()).isEqualTo(true),
                () -> assertEquals(LocalDateTime.of(2022, Month.NOVEMBER, 9, 20, 10, 00), departureEntity1.getDepartureTime())
        );
    }

    @Test
    @DisplayName("Should save a arrivalFlight in the database")
    public void saveArrivalFlight() {

        DepartureEntity departureEntity = DepartureEntity.builder()
                .setIdAirport(1)
                .setStatus(false)
                .setDepartureTime(LocalDateTime.of(2023, Month.NOVEMBER, 23, 16, 30, 00))
                .create();

        crudRepository.save(departureEntity);

        DepartureEntity departureSaved = crudRepository.findById(5).get();

        assertAll(
                () -> assertThat(departureSaved.getIdDeparture()).isEqualTo(departureEntity.getIdDeparture()),
                () -> assertEquals(1, departureSaved.getIdAirport()),
                () -> assertEquals(false, departureSaved.getStatus()),
                () -> assertEquals(LocalDateTime.of(2023, Month.NOVEMBER, 23, 16, 30, 00), departureSaved.getDepartureTime())
        );
    }

    @Test
    @DisplayName("Should return all departure with status true in the database")
    public void findByStatus() {
        List<DepartureEntity> departureEntities = crudRepository.findByStatus(true);

        assertAll(
                () -> assertThat(departureEntities.size()).isEqualTo(4),
                () -> assertTrue(departureEntities.contains(departure)),
                () -> assertEquals(Arrays.asList(1, 2, 3, 4), departureEntities.stream().map(DepartureEntity::getIdDeparture).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 2, 5, 3), departureEntities.stream().map(DepartureEntity::getIdAirport).collect(Collectors.toList())),
                () -> assertThat(departureEntities).filteredOn(
                        departures -> departures.getStatus().equals(true)
                )
        );
    }

    @Test
    @DisplayName("Should return specific departure in the database by a specific idAirport")
    public void getByIdAirport() {
        List<DepartureEntity> departureByAirport = crudRepository.findByIdAirportAndStatus(5, true).get();

        assertAll(
                () -> assertThat(departureByAirport.size()).isEqualTo(1),
                () -> assertFalse(departureByAirport.contains(departure)),
                () -> assertEquals(3, departureByAirport.get(0).getIdDeparture()),
                () -> assertEquals(5, departureByAirport.get(0).getIdAirport())
        );

        List<DepartureEntity> departureWithoutAirport = crudRepository.findByIdAirportAndStatus(8, true).get();

        assertTrue(departureWithoutAirport.isEmpty());
    }

    @Test
    @DisplayName("Should return all departure in the database with one date after the specified and his status = true")
    public void findByDepartureTimeAfterAndStatus() {
        List<DepartureEntity> departureByArrivalTime = crudRepository.findByDepartureTimeAfterAndStatus(LocalDateTime.now(), true).get();

        List<DepartureEntity> departureByBeforeArrivalTime = crudRepository.findByDepartureTimeAfterAndStatus(LocalDateTime.of(2000, Month.APRIL, 01, 01, 00, 00), true).get();

        assertAll(
                () -> assertThat(departureByArrivalTime.size()).isEqualTo(1),
                () -> assertThat(departureByBeforeArrivalTime.size()).isEqualTo(4),
                () -> assertTrue(departureByArrivalTime.contains(departure)),
                () -> assertEquals(Arrays.asList(4), departureByArrivalTime.stream().map(DepartureEntity::getIdDeparture).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(3), departureByArrivalTime.stream().map(DepartureEntity::getIdAirport).collect(Collectors.toList())),
                () -> assertTrue(departureByArrivalTime.stream().map(DepartureEntity::getDepartureTime)
                        .allMatch(time -> time.isAfter(LocalDateTime.now()))),
                () -> assertThat(departureByArrivalTime).filteredOn(
                        arrivalFlight -> arrivalFlight.getStatus().equals(true)
                )
        );
    }

    @Test
    @DisplayName("Should update all departure in the database where the time condition occurs")
    public void updateDepartureEntityStatus() {
        crudRepository.updateDepartureStatus(2);

        List<DepartureEntity> departureEntities = crudRepository.findByStatus(true);

        assertAll(
                () -> assertThat(departureEntities.size()).isEqualTo(3),
                () -> assertTrue(departureEntities.contains(departure)),
                () -> assertEquals(1, departureEntities.get(0).getIdDeparture()),
                () -> assertThat(departureEntities).filteredOn(
                        arrivalFlight -> arrivalFlight.getStatus().equals(true)
                )
        );
    }

    @Test
    @DisplayName("Should update the status to false in the database of all departures that satisfy the time condition")
    public void updateArrival_in_StatusController() {
        List<DepartureEntity> allDepartures = crudRepository.findByStatus(true);

        allDepartures.stream().filter(time ->
                        LocalDateTime.now().until(time.getDepartureTime(),
                                ChronoUnit.HOURS) <= TimeUnit.HOURS.toHours(32) ||
                                time.getDepartureTime().isBefore(LocalDateTime.now()))
                .forEach(departure -> crudRepository.updateDepartureStatus(departure.getIdDeparture()));

        List<DepartureEntity> newAllDepartures = crudRepository.findByStatus(true);

        assertAll(
                () -> Assertions.assertThat(newAllDepartures.size()).isEqualTo(1),
                () -> assertEquals(Arrays.asList(4), newAllDepartures.stream().map(DepartureEntity::getIdDeparture).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(3), newAllDepartures.stream().map(DepartureEntity::getIdAirport).collect(Collectors.toList()))
        );
    }
}