package com.practice.flightbooking.repository;

import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.repository.DepartureRepository;
import com.practice.flightbooking.domain.Departure;
import com.practice.flightbooking.persistence.crud.DepartureCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import com.practice.flightbooking.persistence.mapper.DepartureMapper;
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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartureRepositoryImplTest {

    @Autowired
    private DepartureRepository departureRepository;

    @Autowired
    private DepartureMapper departureMapper;

    @MockBean
    private DepartureCrudRepository departureCrudRepository;

    private Optional<List<DepartureEntity>> optionalDepartures;

    @BeforeEach
    void setUp() {
        DepartureEntity departureEntity1 = DepartureEntity.builder()
                .setIdDeparture(1)
                .setIdAirport(3)
                .setDepartureTime(LocalDateTime.of(2022, Month.OCTOBER, 9, 16, 30, 00))
                .setStatus(true)
                .create();

        DepartureEntity departureEntity2 = DepartureEntity.builder()
                .setIdDeparture(2)
                .setIdAirport(3)
                .setDepartureTime(LocalDateTime.of(2022, Month.NOVEMBER, 9, 16, 30, 00))
                .setStatus(true)
                .create();

        DepartureEntity departureEntity3 = DepartureEntity.builder()
                .setIdDeparture(3)
                .setIdAirport(4)
                .setDepartureTime(LocalDateTime.of(2022, Month.NOVEMBER, 9, 16, 30, 00))
                .setStatus(true)
                .create();

        optionalDepartures = Optional.of(Arrays.asList(departureEntity1, departureEntity2, departureEntity3));
    }

    @Test
    @DisplayName("Should return all departureEntities with status = true and the mapper should transform those entities to departures")
    void getAllDepartures() {
        Mockito.when(departureCrudRepository.findByStatus(true))
                .thenReturn(optionalDepartures.get());

        List<Departure> allDepartures = departureRepository.getAllDepartures();

        assertAll(
                () -> Assertions.assertThat(allDepartures.size()).isEqualTo(3),
                () -> assertEquals(Arrays.asList(1, 2, 3), allDepartures.stream().map(Departure::getDepartureId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(3, 3, 4), allDepartures.stream().map(Departure::getAirportId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return one departureEntity with the specific id and the mapper should transform to departure")
    void getDepartureById() throws Exception {
        Mockito.when(departureCrudRepository.findById(3))
                .thenReturn(Optional.of(optionalDepartures.get().get(2)));

        Departure departureById = departureRepository.getDepartureById(3).get();

        assertEquals(3, departureById.getDepartureId());
    }

    @Test
    @DisplayName("Should return all departureEntities with the specific idAirport and value true, and the mapper should transform to departures")
    void getByIdAirport() throws Exception {
        Mockito.when(departureCrudRepository.findByIdAirportAndStatus(3, true))
                .thenReturn(Optional.of(Arrays.asList(optionalDepartures.get().get(0), optionalDepartures.get().get(1))));

        List<Departure> departureById = departureRepository.getByIdAirport(3).get();

        assertAll(
                () -> assertThat(departureById.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(1, 2), departureById.stream().map(Departure::getDepartureId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(3, 3), departureById.stream().map(Departure::getAirportId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return all departureEntities with one date after the specified and value true, and the mapper should transform to departures")
    void getByDepartureTime() throws Exception {
        DepartureEntity departureEntity = DepartureEntity.builder()
                .setIdDeparture(4)
                .setIdAirport(32)
                .setDepartureTime(LocalDateTime.of(2023, Month.NOVEMBER, 9, 16, 30, 00))
                .setStatus(true)
                .create();

        Mockito.when(departureCrudRepository.findByDepartureTimeAfterAndStatus(LocalDateTime.of(2000, Month.APRIL, 01, 01, 00, 00), true))
                .thenReturn(Optional.of(Arrays.asList(departureEntity)));

        List<Departure> departureByDepartureTime = departureRepository.getByDepartureTime(LocalDateTime.of(2000, Month.APRIL, 01, 01, 00, 00)).get();

        assertAll(
                () -> assertThat(departureByDepartureTime.size()).isEqualTo(1),
                () -> assertEquals(Arrays.asList(4), departureByDepartureTime.stream().map(Departure::getDepartureId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(32), departureByDepartureTime.stream().map(Departure::getAirportId).collect(Collectors.toList())),
                () -> assertTrue(departureByDepartureTime.stream().map(Departure::getDepartureTime)
                        .allMatch(time -> time.isAfter(LocalDateTime.now())))
        );
    }

    @Test
    @DisplayName("Should save one departureEntity and return it with the mapper to departure")
    void saveDeparture() throws Exception {
        DepartureEntity departureEntity = DepartureEntity.builder()
                .setIdDeparture(32)
                .setIdAirport(21)
                .setDepartureTime(LocalDateTime.of(2025, Month.JANUARY, 21, 23, 10, 11))
                .setStatus(true)
                .create();

        Mockito.when(departureCrudRepository.save(ArgumentMatchers.any())).thenReturn(departureEntity);

        Departure saveDeparture = departureRepository.saveDeparture(Mappers.getMapper(DepartureMapper.class).toDeparture(departureEntity));

        assertAll(
                () -> assertEquals(departureEntity.getIdDeparture(), saveDeparture.getDepartureId()),
                () -> assertEquals(departureEntity.getIdAirport(), saveDeparture.getAirportId()),
                () -> assertEquals(departureEntity.getDepartureTime(), saveDeparture.getDepartureTime())
        );
    }
}