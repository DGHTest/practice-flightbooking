package com.practice.flightbooking.service;

import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.repository.ArrivalFlightRepository;
import com.practice.flightbooking.domain.service.ArrivalFlightService;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.mapper.ArrivalFlightMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
class ArrivalFlightServiceTest {

    @Autowired
    private ArrivalFlightService arrivalFlightService;

    @MockBean
    private ArrivalFlightRepository arrivalFlightRepository;

    private List<ArrivalFlight> arrivalFlights;

    @BeforeEach
    void setUp() {
        ArrivalFlight arrivalFlight1 = ArrivalFlight.builder()
                .setArrivalFlightId(23)
                .setAirportId(213)
                .setArrivalTime(LocalDateTime.of(2023, Month.APRIL, 12, 15, 32, 54))
                .create();

        ArrivalFlight arrivalFlight2 = ArrivalFlight.builder()
                .setArrivalFlightId(33)
                .setAirportId(343)
                .setArrivalTime(LocalDateTime.of(2023, Month.APRIL, 12, 15, 32, 54))
                .create();

        ArrivalFlight arrivalFlight3 = ArrivalFlight.builder()
                .setArrivalFlightId(433)
                .setAirportId(213)
                .setArrivalTime(LocalDateTime.of(2023, Month.APRIL, 12, 15, 32, 54))
                .create();

        arrivalFlights = Arrays.asList(arrivalFlight1, arrivalFlight2, arrivalFlight3);
    }

    @Test
    @DisplayName("Should return all arrivalFlights using the repository")
    void getAllArrivalFlights() {
        Mockito.when(arrivalFlightRepository.getAllArrivalFlights())
                .thenReturn(arrivalFlights);

        List<ArrivalFlight> allArrivalFlights = arrivalFlightService.getAllArrivalFlights();

        assertAll(
                () -> Assertions.assertThat(allArrivalFlights.size()).isEqualTo(3),
                () -> assertEquals(Arrays.asList(23, 33, 433), allArrivalFlights.stream().map(ArrivalFlight::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(213, 343, 213), allArrivalFlights.stream().map(ArrivalFlight::getAirportId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return one arrivalFlight with the specific id using the repository")
    void getArrivalById() throws Exception {
        Mockito.when(arrivalFlightRepository.getArrivalById(33))
                .thenReturn(arrivalFlights.get(1));

        ArrivalFlight arrivalFlightById = arrivalFlightService.getArrivalById(33);

        assertAll(
                () -> assertEquals(33, arrivalFlightById.getArrivalFlightId()),
                () -> assertEquals(343, arrivalFlightById.getAirportId())
        );
    }

    @Test
    @DisplayName("Should return all arrivalFlights with the specific idAirport using the repository")
    void getByIdAirport() throws Exception {
        Mockito.when(arrivalFlightRepository.getByIdAirport(213))
                .thenReturn(Arrays.asList(arrivalFlights.get(0), arrivalFlights.get(2)));

        List<ArrivalFlight> arrivalByIdAirport = arrivalFlightService.getByIdAirport(213);

        assertAll(
                () -> assertThat(arrivalByIdAirport.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(23, 433), arrivalByIdAirport.stream().map(ArrivalFlight::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(213, 213), arrivalByIdAirport.stream().map(ArrivalFlight::getAirportId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return all arrivalFlights with one date after the specified using the repository")
    void getByArrivalTime() throws Exception {
        Mockito.when(arrivalFlightRepository.getByArrivalTime(LocalDateTime.of(2000, Month.APRIL, 01, 01, 00, 00)))
                .thenReturn(arrivalFlights);

        List<ArrivalFlight> arrivalsByArrivalTime = arrivalFlightService.getByArrivalTime(LocalDateTime.of(2000, Month.APRIL, 01, 01, 00, 00));

        assertAll(
                () -> assertThat(arrivalsByArrivalTime.size()).isEqualTo(3),
                () -> assertEquals(Arrays.asList(23, 33, 433), arrivalsByArrivalTime.stream().map(ArrivalFlight::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(213, 343, 213), arrivalsByArrivalTime.stream().map(ArrivalFlight::getAirportId).collect(Collectors.toList())),
                () -> assertTrue(arrivalsByArrivalTime.stream().map(ArrivalFlight::getArrivalTime)
                        .allMatch(time -> time.isAfter(LocalDateTime.now())))
        );
    }

    @Test
    @DisplayName("Should save one arrivalFlight and return it with the use of repository")
    void saveArrival() throws Exception {
        ArrivalFlight arrivalFlight = ArrivalFlight.builder()
                .setArrivalFlightId(32)
                .setAirportId(21)
                .setArrivalTime(LocalDateTime.of(2025, Month.JANUARY, 21, 23, 10, 11))
                .create();

        Mockito.when(arrivalFlightRepository.saveArrival(ArgumentMatchers.any())).thenReturn(arrivalFlight);

        ArrivalFlight saveArrivalFlight = arrivalFlightService.saveArrival(arrivalFlight);

        assertAll(
                () -> assertEquals(arrivalFlight.getArrivalFlightId(), saveArrivalFlight.getArrivalFlightId()),
                () -> assertEquals(arrivalFlight.getAirportId(), saveArrivalFlight.getAirportId()),
                () -> assertEquals(arrivalFlight.getArrivalTime(), saveArrivalFlight.getArrivalTime())
        );
    }
}