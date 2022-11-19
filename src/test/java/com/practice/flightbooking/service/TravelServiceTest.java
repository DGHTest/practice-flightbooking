package com.practice.flightbooking.service;

import com.practice.flightbooking.domain.Travel;
import com.practice.flightbooking.domain.repository.TravelRepository;
import com.practice.flightbooking.domain.service.TravelService;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import com.practice.flightbooking.persistence.mapper.TravelMapper;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TravelServiceTest {

    @Autowired
    private TravelService travelService;

    @MockBean
    private TravelRepository travelRepository;

    private List<Travel> travels;

    @BeforeEach
    void setUp() {
        Travel travel1 = Travel.builder()
                .setTravelId(1)
                .setArrivalFlightId(1)
                .setDepartureId(4)
                .setPrice(BigDecimal.valueOf(15000.00))
                .create();

        Travel travel2 = Travel.builder()
                .setTravelId(2)
                .setArrivalFlightId(6)
                .setDepartureId(4)
                .setPrice(BigDecimal.valueOf(12000.00))
                .create();

        Travel travel3 = Travel.builder()
                .setTravelId(3)
                .setArrivalFlightId(6)
                .setDepartureId(6)
                .setPrice(BigDecimal.valueOf(18500.00))
                .create();

        travels = Arrays.asList(travel1, travel2, travel3);
    }

    @Test
    @DisplayName("Should return all travels using the repository")
    void getAllTravels() {
        Mockito.when(travelRepository.getAllTravels())
                .thenReturn(travels);

        List<Travel> allTravels = travelService.getAllTravels();

        assertAll(
                () -> Assertions.assertThat(allTravels.size()).isEqualTo(3),
                () -> assertEquals(Arrays.asList(1, 2, 3), allTravels.stream().map(Travel::getTravelId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 6, 6), allTravels.stream().map(Travel::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(4, 4, 6), allTravels.stream().map(Travel::getDepartureId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList("15000.0", "12000.0", "18500.0"), allTravels.stream().map(travel -> travel.getPrice().toString()).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return one travel with the specific id using the repository")
    void getTravelById() throws Exception {
        Mockito.when(travelRepository.getTravelById(2))
                .thenReturn(travels.get(1));

        Travel travelById = travelService.getTravelById(2);

        assertEquals(2, travelById.getTravelId());
    }

    @Test
    @DisplayName("Should return all travels with the specific idArrivalFlight using the repository")
    void getByIdArrivalFlight() throws Exception {
        Mockito.when(travelRepository.getByIdArrivalFlight(6))
                .thenReturn(Arrays.asList(travels.get(1), travels.get(2)));

        List<Travel> travelsByIdArrivalFlight = travelService.getByIdArrivalFlight(6);

        assertAll(
                () -> assertThat(travelsByIdArrivalFlight.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(2, 3), travelsByIdArrivalFlight.stream().map(Travel::getTravelId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(6, 6), travelsByIdArrivalFlight.stream().map(Travel::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(4, 6), travelsByIdArrivalFlight.stream().map(Travel::getDepartureId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return all travels with the specific idDeparture using the repository")
    void getByIdDeparture() throws Exception {
        Mockito.when(travelRepository.getByIdDeparture(4))
                .thenReturn(Arrays.asList(travels.get(0), travels.get(1)));

        List<Travel> travelsByIdArrivalFlight = travelService.getByIdDeparture(4);

        assertAll(
                () -> assertThat(travelsByIdArrivalFlight.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(1, 2), travelsByIdArrivalFlight.stream().map(Travel::getTravelId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 6), travelsByIdArrivalFlight.stream().map(Travel::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(4, 4), travelsByIdArrivalFlight.stream().map(Travel::getDepartureId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should save one travel and return it using the repository")
    void saveTravel() throws Exception {
        Travel travel = Travel.builder()
                .setTravelId(51)
                .setArrivalFlightId(32)
                .setDepartureId(26)
                .setPrice(BigDecimal.valueOf(17610.00))
                .create();

        Mockito.when(travelRepository.saveTravel(ArgumentMatchers.any())).thenReturn(travel);

        Travel saveTravel = travelService.saveTravel(travel);

        assertAll(
                () -> assertEquals(travel.getTravelId(), saveTravel.getTravelId()),
                () -> assertEquals(travel.getArrivalFlightId(), saveTravel.getArrivalFlightId()),
                () -> assertEquals(travel.getDepartureId(), saveTravel.getDepartureId()),
                () -> assertEquals(travel.getPrice(), saveTravel.getPrice())
        );
    }
}