package com.practice.flightbooking.service;

import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.repository.AirportRepository;
import com.practice.flightbooking.domain.service.AirportService;
import com.practice.flightbooking.persistence.AirportRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@SpringBootTest
class AirportServiceTest {

    @Autowired
    private AirportService airportService;

    @MockBean
    private AirportRepository airportRepository;

    private List<Airport> airportList;

    @BeforeEach
    void setUp() {
        Airport airport1 = Airport.builder()
                .setAirportId(12)
                .setCountry("Mexico")
                .setState("Sonora")
                .setCity("Hermosillo")
                .setIata("LOL")
                .create();

        Airport airport2 = Airport.builder()
                .setAirportId(322)
                .setCountry("Mexico")
                .setState("Chiapas")
                .setCity("Tuxtla Gutiérrez")
                .setIata("LOL")
                .create();

        airportList = Arrays.asList(airport1, airport2);
    }

    @Test
    @DisplayName("Should return an airport that match with a specific id using the repository")
    void getById() {
        Mockito.when(airportRepository.getById(322))
                .thenReturn(Optional.of(airportList.get(1)));

        Airport airportById = airportService.getById(322).get();

        assertAll(
                () -> assertEquals(322, airportById.getAirportId())
        );
    }

    @Test
    @DisplayName("Should return all airports that match with a specific country using the repository")
    void getByCountry() {
        Mockito.when(airportRepository.getByCountry("Mexico"))
                .thenReturn(Optional.of(airportList));

        List<Airport> airportsByCountry = airportService.getByCountry("Mexico").get();

        assertAll(
                () -> Assertions.assertThat(airportsByCountry.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(12, 322), airportsByCountry.stream().map(Airport::getAirportId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList("Mexico", "Mexico"), airportsByCountry.stream().map(Airport::getCountry).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return all airports that match with a specific state using the repository")
    void getByState() {
        Mockito.when(airportRepository.getByState("Sonora"))
                .thenReturn(Optional.of(Arrays.asList(airportList.get(0))));

        List<Airport> airportsByState = airportService.getByState("Sonora").get();

        assertAll(
                () -> Assertions.assertThat(airportsByState.size()).isEqualTo(1),
                () -> assertEquals(Arrays.asList(12), airportsByState.stream().map(Airport::getAirportId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList("Sonora"), airportsByState.stream().map(Airport::getState).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return all airports that match with a specific city using the repository")
    void getByCity() {
        Mockito.when(airportRepository.getByCity("Tuxtla Gutiérrez"))
                .thenReturn(Optional.of(Arrays.asList(airportList.get(1))));


        List<Airport> airportServiceByCity = airportService.getByCity("Tuxtla Gutiérrez").get();

        assertAll(
                () -> Assertions.assertThat(airportServiceByCity.size()).isEqualTo(1),
                () -> assertEquals(Arrays.asList(322), airportServiceByCity.stream().map(Airport::getAirportId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList("Tuxtla Gutiérrez"), airportServiceByCity.stream().map(Airport::getCity).collect(Collectors.toList()))
        );
    }
}