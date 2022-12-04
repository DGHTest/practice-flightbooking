package com.practice.flightbooking.repository;

import com.practice.flightbooking.domain.repository.TravelRepository;
import com.practice.flightbooking.domain.Travel;
import com.practice.flightbooking.persistence.crud.TravelCrudRepository;
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
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class TravelRepositoryImplTest {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private TravelMapper travelMapper;

    @MockBean
    private TravelCrudRepository travelCrudRepository;

    private Optional<List<TravelEntity>> optionalTravels;

    @BeforeEach
    void setUp() {
        TravelEntity travelEntity1 = TravelEntity.builder()
                .setIdTravel(1)
                .setIdArrivalFlight(1)
                .setIdDeparture(4)
                .setPrice(BigDecimal.valueOf(15000.00))
                .setStatus(true)
                .create();

        TravelEntity travelEntity2 = TravelEntity.builder()
                .setIdTravel(2)
                .setIdArrivalFlight(6)
                .setIdDeparture(4)
                .setPrice(BigDecimal.valueOf(12000.00))
                .setStatus(true)
                .create();

        TravelEntity travelEntity3 = TravelEntity.builder()
                .setIdTravel(3)
                .setIdArrivalFlight(6)
                .setIdDeparture(6)
                .setPrice(BigDecimal.valueOf(18500.00))
                .setStatus(true)
                .create();

        optionalTravels = Optional.of(Arrays.asList(travelEntity1, travelEntity2, travelEntity3));
    }

    @Test
    @DisplayName("Should return all travelEntities with status = true and the mapper should transform those entities to travels")
    void getAllTravels() {
        Mockito.when(travelCrudRepository.findByStatus(true))
                .thenReturn(optionalTravels.get());

        List<Travel> allTravels = travelRepository.getAllTravels();

        assertAll(
                () -> Assertions.assertThat(allTravels.size()).isEqualTo(3),
                () -> assertEquals(Arrays.asList(1, 2, 3), allTravels.stream().map(Travel::getTravelId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 6, 6), allTravels.stream().map(Travel::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(4, 4, 6), allTravels.stream().map(Travel::getDepartureId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList("15000.0", "12000.0", "18500.0"), allTravels.stream().map(travel -> travel.getPrice().toString()).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return one travelEntity with the specific id and the mapper should transform to travel")
    void getTravelById() {
        Mockito.when(travelCrudRepository.findById(2))
                .thenReturn(Optional.of(optionalTravels.get().get(1)));

        Travel travelById = travelRepository.getTravelById(2).get();

        assertEquals(2, travelById.getTravelId());
    }

    @Test
    @DisplayName("Should return all travelEntities with the specific idArrivalFlight and value true, and the mapper should transform to travels")
    void getByIdArrivalFlight() {
        Mockito.when(travelCrudRepository.findByIdArrivalFlightAndStatus(6, true))
                .thenReturn(Optional.of(Arrays.asList(optionalTravels.get().get(1), optionalTravels.get().get(2))));

        List<Travel> travelsByIdArrivalFlight = travelRepository.getByIdArrivalFlight(6).get();

        assertAll(
                () -> assertThat(travelsByIdArrivalFlight.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(2, 3), travelsByIdArrivalFlight.stream().map(Travel::getTravelId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(6, 6), travelsByIdArrivalFlight.stream().map(Travel::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(4, 6), travelsByIdArrivalFlight.stream().map(Travel::getDepartureId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should return all travelEntities with the specific idDeparture and value true, and the mapper should transform to travels")
    void getByIdDeparture() {
        Mockito.when(travelCrudRepository.findByIdDepartureAndStatus(4, true))
                .thenReturn(Optional.of(Arrays.asList(optionalTravels.get().get(0), optionalTravels.get().get(1))));

        List<Travel> travelsByIdArrivalFlight = travelRepository.getByIdDeparture(4).get();

        assertAll(
                () -> assertThat(travelsByIdArrivalFlight.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(1, 2), travelsByIdArrivalFlight.stream().map(Travel::getTravelId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 6), travelsByIdArrivalFlight.stream().map(Travel::getArrivalFlightId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(4, 4), travelsByIdArrivalFlight.stream().map(Travel::getDepartureId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should save one travelEntity and return it with the mapper to travel or throw an error if the id already exist")
    void saveTravel() {
        TravelEntity travelEntity = TravelEntity.builder()
                .setIdTravel(51)
                .setIdArrivalFlight(32)
                .setIdDeparture(26)
                .setPrice(BigDecimal.valueOf(17610.00))
                .setStatus(true)
                .create();

        Mockito.when(travelCrudRepository.save(ArgumentMatchers.any())).thenReturn(travelEntity);

        Travel saveTravel = travelRepository.saveTravel(travelMapper.toTravel(travelEntity));

        assertAll(
                () -> assertEquals(travelEntity.getIdTravel(), saveTravel.getTravelId()),
                () -> assertEquals(travelEntity.getIdArrivalFlight(), saveTravel.getArrivalFlightId()),
                () -> assertEquals(travelEntity.getIdDeparture(), saveTravel.getDepartureId()),
                () -> assertEquals(travelEntity.getPrice(), saveTravel.getPrice())
        );
    }
}