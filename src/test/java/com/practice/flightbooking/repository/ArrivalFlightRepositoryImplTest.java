package com.practice.flightbooking.repository;

import com.practice.flightbooking.domain.repository.ArrivalFlightRepository;
import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.persistence.crud.ArrivalCrudRepository;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import com.practice.flightbooking.persistence.mapper.ArrivalFlightMapper;
import org.assertj.core.api.Assertions;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("dev")
class ArrivalFlightRepositoryImplTest {

    @Autowired
    private ArrivalFlightRepository arrivalFlightRepository;

    @Autowired
    private ArrivalFlightMapper arrivalFlightMapper;

    @MockBean
    private ArrivalCrudRepository arrivalCrudRepository;

    private Optional<List<ArrivalFlightEntity>> optionalArrivalFlights;

    @BeforeEach
    void setUp() {
        ArrivalFlightEntity arrivalFlightEntity1 = ArrivalFlightEntity.builder()
                .setIdArrivalFlight(1)
                .setIdAirport(3)
                .setArrivalTime(LocalDateTime.of(2022, Month.OCTOBER, 9, 16, 30, 00))
                .setStatus(true)
                .create();

        ArrivalFlightEntity arrivalFlightEntity2 = ArrivalFlightEntity.builder()
                .setIdArrivalFlight(2)
                .setIdAirport(3)
                .setArrivalTime(LocalDateTime.of(2022, Month.NOVEMBER, 9, 16, 30, 00))
                .setStatus(true)
                .create();

        ArrivalFlightEntity arrivalFlightEntity3 = ArrivalFlightEntity.builder()
                .setIdArrivalFlight(3)
                .setIdAirport(4)
                .setArrivalTime(LocalDateTime.of(2022, Month.NOVEMBER, 9, 16, 30, 00))
                .setStatus(true)
                .create();

        optionalArrivalFlights = Optional.of(Arrays.asList(arrivalFlightEntity1, arrivalFlightEntity2, arrivalFlightEntity3));
    }

    @Test
    @DisplayName("Should return all arrivalFlightEntities with status = true and the mapper should transform those entities to arrivalFlights")
    void getAllArrivalFlights() {
        Mockito.when(arrivalCrudRepository.findByStatus(true))
                .thenReturn(optionalArrivalFlights.get());

        List<ArrivalFlight> allArrivalFlights = arrivalFlightRepository.getAllArrivalFlights();

        assertAll(
                () -> Assertions.assertThat(allArrivalFlights.size()).isEqualTo(3),
                () -> assertEquals(Arrays.asList(1, 2, 3), allArrivalFlights.stream().map(ArrivalFlight::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(3, 3, 4), allArrivalFlights.stream().map(ArrivalFlight::getAirportId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return one arrivalFlightEntity with the specific id and the mapper should transform to arrivalFlight")
    void getArrivalById() throws Exception {
        Mockito.when(arrivalCrudRepository.findById(3))
                .thenReturn(Optional.of(optionalArrivalFlights.get().get(2)));

        ArrivalFlight airportById = arrivalFlightRepository.getArrivalById(3).get();

        assertEquals(3, airportById.getArrivalFlightId());
    }

    @Test
    @DisplayName("Should return all arrivalFlightEntities with the specific idAirport and value true, and the mapper should transform to arrivalFlights")
    void getByIdAirport() throws Exception {
        Mockito.when(arrivalCrudRepository.findByIdAirportAndStatus(3, true))
                .thenReturn(Optional.of(Arrays.asList(optionalArrivalFlights.get().get(0), optionalArrivalFlights.get().get(1))));

        List<ArrivalFlight> airportById = arrivalFlightRepository.getByIdAirport(3).get();

        assertAll(
                () -> assertThat(airportById.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(1, 2), airportById.stream().map(ArrivalFlight::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(3, 3), airportById.stream().map(ArrivalFlight::getAirportId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return all arrivalFlightEntities with one date after the specified and value true, and the mapper should transform to arrivalFlights")
    void getByArrivalTime() throws Exception {
        ArrivalFlightEntity arrivalFlightEntity = ArrivalFlightEntity.builder()
                .setIdArrivalFlight(4)
                .setIdAirport(32)
                .setArrivalTime(LocalDateTime.of(2023, Month.NOVEMBER, 9, 16, 30, 00))
                .setStatus(true)
                .create();

        Mockito.when(arrivalCrudRepository.findByArrivalTimeAfterAndStatus(LocalDateTime.of(2000, Month.APRIL, 01, 01, 00, 00), true))
                .thenReturn(Optional.of(Arrays.asList(arrivalFlightEntity)));

        List<ArrivalFlight> airportByArrivalTime = arrivalFlightRepository.getByArrivalTime(LocalDateTime.of(2000, Month.APRIL, 01, 01, 00, 00)).get();

        assertAll(
                () -> assertThat(airportByArrivalTime.size()).isEqualTo(1),
                () -> assertEquals(Arrays.asList(4), airportByArrivalTime.stream().map(ArrivalFlight::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(32), airportByArrivalTime.stream().map(ArrivalFlight::getAirportId).collect(Collectors.toList())),
                () -> assertTrue(airportByArrivalTime.stream().map(ArrivalFlight::getArrivalTime)
                        .allMatch(time -> time.isAfter(LocalDateTime.now())))
        );
    }

    @Test
    @DisplayName("Should save one arrivalFlightEntity and return it with the mapper to arrivalFlight")
    void saveArrival() throws Exception {
        ArrivalFlightEntity arrivalFlightEntity = ArrivalFlightEntity.builder()
                .setIdArrivalFlight(32)
                .setIdAirport(21)
                .setAirportEntity(AirportEntity.builder().setIdAirport(3).setName("tgsg").setState("esfesfa").setCity("fesgasga").setIata("RGH").create())
                .setArrivalTime(LocalDateTime.of(2025, Month.JANUARY, 21, 23, 10, 11))
                .setStatus(true)
                .create();

        Mockito.when(arrivalCrudRepository.save(ArgumentMatchers.any())).thenReturn(arrivalFlightEntity);

        ArrivalFlight saveArrivalFlight = arrivalFlightRepository.saveArrival(arrivalFlightMapper.toArrivalFlight(arrivalFlightEntity));

        assertAll(
                () -> assertEquals(arrivalFlightEntity.getIdArrivalFlight(), saveArrivalFlight.getArrivalFlightId()),
                () -> assertEquals(arrivalFlightEntity.getIdAirport(), saveArrivalFlight.getAirportId()),
                () -> assertEquals(arrivalFlightEntity.getArrivalTime(), saveArrivalFlight.getArrivalTime())
        );
    }
}