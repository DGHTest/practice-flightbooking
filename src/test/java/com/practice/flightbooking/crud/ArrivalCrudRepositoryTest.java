package com.practice.flightbooking.crud;

import com.practice.flightbooking.persistence.crud.ArrivalCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
@Sql("/db/flightbooking_data.sql")
class ArrivalCrudRepositoryTest {

    @Autowired
    private ArrivalCrudRepository crudRepository;

    @Autowired
    private TestEntityManager entityManager;

    private ArrivalFlightEntity arrival;

    @BeforeEach
    void setUp() {

         arrival = ArrivalFlightEntity.builder()
                        .setIdAirport(3)
                        .setArrivalTime(LocalDateTime.of(2022, Month.NOVEMBER, 23, 16, 30, 00))
                        .setStatus(true)
                        .create();

        entityManager.persist(arrival);
        entityManager.flush();
    }

    @Test
    @DisplayName("Should return a arrivalFlight of the data.sql")
    public void findById() {

        ArrivalFlightEntity arrivalFlightEntity = crudRepository.findById(4).get();

        assertAll("Test with a persist entity",
                () -> assertThat(arrivalFlightEntity.getIdArrivalFlight()).isEqualTo(arrival.getIdArrivalFlight()),
                () -> assertThat(arrivalFlightEntity.getIdAirport()).isEqualTo(arrival.getIdAirport()),
                () -> assertThat(arrivalFlightEntity.getStatus()).isEqualTo(arrival.getStatus()),
                () -> assertEquals(arrival.getArrivalTime(), arrivalFlightEntity.getArrivalTime())
        );

        ArrivalFlightEntity arrivalFlightEntity1 = crudRepository.findById(2).get();

        assertAll("Test with the data.sql",
                () -> assertThat(arrivalFlightEntity1.getIdArrivalFlight()).isEqualTo(2),
                () -> assertThat(arrivalFlightEntity1.getIdAirport()).isEqualTo(3),
                () -> assertThat(arrivalFlightEntity1.getStatus()).isEqualTo(true),
                () -> assertEquals(LocalDateTime.of(2022, Month.NOVEMBER, 9, 16, 30, 00), arrivalFlightEntity1.getArrivalTime())
        );
    }

    @Test
    @DisplayName("Should save a arrivalFlight in the database")
    public void saveArrivalFlight() {

        ArrivalFlightEntity arrivalFlight = ArrivalFlightEntity.builder()
                .setIdAirport(1)
                .setStatus(false)
                .setArrivalTime(LocalDateTime.of(2023, Month.NOVEMBER, 23, 16, 30, 00))
                .create();

        ArrivalFlightEntity arrivalFlightSaved = crudRepository.save(arrivalFlight);

        //ArrivalFlightEntity arrivalFlightSaved = crudRepository.findById(5).get();

        assertAll(
                () -> assertEquals(arrivalFlight.getIdAirport(), arrivalFlightSaved.getIdAirport()),
                () -> assertEquals(arrivalFlight.getStatus(), arrivalFlightSaved.getStatus()),
                () -> assertEquals(arrivalFlight.getArrivalTime(), arrivalFlightSaved.getArrivalTime())
        );
    }

    @Test
    @DisplayName("Should return all arrivalFlight with status true in the database")
    public void findByStatus() {
        List<ArrivalFlightEntity> allArrivalFlight = crudRepository.findByStatus(true);

        assertAll(
                () -> assertThat(allArrivalFlight.size()).isEqualTo(4),
                () -> assertTrue(allArrivalFlight.contains(arrival)),
                () -> assertEquals(Arrays.asList(1, 2, 3, 4), allArrivalFlight.stream().map(travel -> travel.getIdArrivalFlight()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(3, 3, 4, 3), allArrivalFlight.stream().map(travel -> travel.getIdAirport()).collect(Collectors.toList())),
                () -> assertThat(allArrivalFlight).filteredOn(
                        arrivalFlight -> arrivalFlight.getStatus().equals(true)
                )
        );
    }

    @Test
    @DisplayName("Should return specific arrivalFlight in the database by a specific idAirport")
    public void getByIdAirport() {
        List<ArrivalFlightEntity> arrivalFlightByAirport = crudRepository.findByIdAirportAndStatus(3, true).get();

        assertAll(
                () -> assertThat(arrivalFlightByAirport.size()).isEqualTo(3),
                () -> assertTrue(arrivalFlightByAirport.contains(arrival)),
                () -> assertEquals(Arrays.asList(1, 2, 4), arrivalFlightByAirport.stream().map(travel -> travel.getIdArrivalFlight()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(3, 3, 3), arrivalFlightByAirport.stream().map(travel -> travel.getIdAirport()).collect(Collectors.toList())),
                () -> assertThat(arrivalFlightByAirport).filteredOn(
                        arrivalFlight -> arrivalFlight.getStatus().equals(true)
                )
        );

        List<ArrivalFlightEntity> arrivalFlightWithoutAirport = crudRepository.findByIdAirportAndStatus(8, true).get();

        assertTrue(arrivalFlightWithoutAirport.isEmpty());
    }

    @Test
    @DisplayName("Should update the status of a specific arrivalFlight by his id in the database")
    public void updateArrivalFlightEntityStatus() {
        crudRepository.updateArrivalStatus(1);

        List<ArrivalFlightEntity> allArrivalFlight = crudRepository.findByStatus(true);

        assertAll(
                () -> assertThat(allArrivalFlight.size()).isEqualTo(3),
                () -> assertTrue(allArrivalFlight.contains(arrival)),
                () -> assertEquals(2, allArrivalFlight.get(0).getIdArrivalFlight()),
                () -> assertThat(allArrivalFlight).filteredOn(
                        arrivalFlight -> arrivalFlight.getStatus().equals(true)
                )
        );
    }

    @Test
    @DisplayName("Should update the status to false in the database of all arrivalFlights that satisfy the time condition")
    public void updateArrival_in_StatusController() {
        List<ArrivalFlightEntity> allArrivals = crudRepository.findByStatus(true);

        allArrivals.stream().filter(time ->
                        LocalDateTime.now().until(time.getArrivalTime(),
                                ChronoUnit.HOURS) <= TimeUnit.HOURS.toHours(32) ||
                        time.getArrivalTime().isBefore(LocalDateTime.now()))
                .forEach(arrival -> crudRepository.updateArrivalStatus(arrival.getIdArrivalFlight()));

        List<ArrivalFlightEntity> newAllArrivals = crudRepository.findByStatus(true);

        assertAll(
                () -> Assertions.assertThat(newAllArrivals.size()).isEqualTo(1),
                () -> assertEquals(Arrays.asList(4), newAllArrivals.stream().map(travel -> travel.getIdArrivalFlight()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(3), newAllArrivals.stream().map(travel -> travel.getIdAirport()).collect(Collectors.toList()))
        );
    }
}