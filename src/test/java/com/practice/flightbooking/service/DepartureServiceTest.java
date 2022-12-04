package com.practice.flightbooking.service;

import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.Departure;
import com.practice.flightbooking.domain.repository.DepartureRepository;
import com.practice.flightbooking.domain.service.DepartureService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
class DepartureServiceTest {

    @Autowired
    private DepartureService departureService;

    @MockBean
    private DepartureRepository departureRepository;

    private List<Departure> departures;

    @BeforeEach
    void setUp() {
        Departure departure1 = Departure.builder()
                .setDepartureId(23)
                .setAirportId(213)
                .setDepartureTime(LocalDateTime.of(2023, Month.APRIL, 12, 15, 32, 54))
                .create();

        Departure departure2 = Departure.builder()
                .setDepartureId(33)
                .setAirportId(343)
                .setDepartureTime(LocalDateTime.of(2023, Month.APRIL, 12, 15, 32, 54))
                .create();

        Departure departure3 = Departure.builder()
                .setDepartureId(433)
                .setAirportId(213)
                .setDepartureTime(LocalDateTime.of(2023, Month.APRIL, 12, 15, 32, 54))
                .create();

        departures = Arrays.asList(departure1, departure2, departure3);
    }

    @Test
    @DisplayName("Should return all departures using the repository")
    void getAllDepartures() {
        Mockito.when(departureRepository.getAllDepartures())
                .thenReturn(departures);

        List<Departure> allDepartures = departureService.getAllDepartures();

        assertAll(
                () -> Assertions.assertThat(allDepartures.size()).isEqualTo(3),
                () -> assertEquals(Arrays.asList(23, 33, 433), allDepartures.stream().map(Departure::getDepartureId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(213, 343, 213), allDepartures.stream().map(Departure::getAirportId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return one departure with the specific id using the repository")
    void getDepartureById() throws Exception {
        Mockito.when(departureRepository.getDepartureById(33))
                .thenReturn(Optional.of(departures.get(1)));

        Departure departureById = departureService.getDepartureById(33).get();

        assertAll(
                () -> assertEquals(33, departureById.getDepartureId()),
                () -> assertEquals(343, departureById.getAirportId())
        );
    }

    @Test
    @DisplayName("Should return all departures with the specific idAirport using the repository")
    void getByIdAirport() throws Exception {
        Mockito.when(departureRepository.getByIdAirport(213))
                .thenReturn(Optional.of(Arrays.asList(departures.get(0), departures.get(2))));

        List<Departure> departureByIdAirport = departureService.getByIdAirport(213).get();

        assertAll(
                () -> assertThat(departureByIdAirport.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(23, 433), departureByIdAirport.stream().map(Departure::getDepartureId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(213, 213), departureByIdAirport.stream().map(Departure::getAirportId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return all departures with one date after the specified using the repository")
    void getByDepartureTime() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2000, Month.APRIL, 1, 1, 0, 0);

        Mockito.when(departureRepository.getByDepartureTime(localDateTime))
                .thenReturn(Optional.of(departures));

        List<Departure> departuresByArrivalTime = departureService.getByDepartureTime(localDateTime).get();

        assertAll(
                () -> assertThat(departuresByArrivalTime.size()).isEqualTo(3),
                () -> assertEquals(Arrays.asList(23, 33, 433), departuresByArrivalTime.stream().map(Departure::getDepartureId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(213, 343, 213), departuresByArrivalTime.stream().map(Departure::getAirportId).collect(Collectors.toList())),
                () -> assertTrue(departuresByArrivalTime.stream().map(Departure::getDepartureTime)
                        .allMatch(time -> time.isAfter(LocalDateTime.now())))
        );
    }

    @Test
    @DisplayName("Should save one departure and return it with the use of repository")
    void saveDeparture() throws Exception {
        Departure departure = Departure.builder()
                .setDepartureId(32)
                .setAirportId(21)
                .setDepartureTime(LocalDateTime.of(2025, Month.JANUARY, 21, 23, 10, 11))
                .create();

        Mockito.when(departureRepository.saveDeparture(ArgumentMatchers.any())).thenReturn(departure);

        Departure saveDeparture = departureService.saveDeparture(departure);

        assertAll(
                () -> assertEquals(departure.getDepartureId(), saveDeparture.getDepartureId()),
                () -> assertEquals(departure.getAirportId(), saveDeparture.getAirportId()),
                () -> assertEquals(departure.getDepartureTime(), saveDeparture.getDepartureTime())
        );
    }
}