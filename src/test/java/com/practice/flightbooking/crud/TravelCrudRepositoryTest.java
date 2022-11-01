package com.practice.flightbooking.crud;

import com.practice.flightbooking.persistence.crud.AirportCrudRepository;
import com.practice.flightbooking.persistence.crud.ArrivalCrudRepository;
import com.practice.flightbooking.persistence.crud.PassengerCrudRepository;
import com.practice.flightbooking.persistence.crud.TravelCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("/db/flightbooking_data.sql")
class TravelCrudRepositoryTest {

    @Autowired
    private TravelCrudRepository crudRepository;

    @Autowired
    private ArrivalCrudRepository arrivalCrudRepository;

    @Autowired
    private TestEntityManager entityManager;

    private TravelEntity travel;

    @BeforeEach
    void setUp() {
        travel = new TravelEntity.Builder()
                .setIdArrivalFlight(1)
                .setIdDeparture(2)
                .setPrice(new BigDecimal("500.01"))
                .setStatus(false)
                .create();

        entityManager.persist(travel);
        entityManager.flush();
    }

    @Test
    @DisplayName("Should return a travel of the data.sql")
    public void findById() {

        TravelEntity findId = crudRepository.findById(4).get();

        assertAll("Test with a persist entity",
                () -> assertThat(findId.getIdTravel()).isEqualTo(travel.getIdTravel()),
                () -> assertEquals(travel.getIdArrivalFlight(), findId.getIdArrivalFlight()),
                () -> assertEquals(travel.getIdDeparture(), findId.getIdDeparture()),
                () -> assertEquals(travel.getPrice().toString(), findId.getPrice().toString()),
                () -> assertEquals(travel.getStatus(), findId.getStatus())

        );

        TravelEntity findId2 = crudRepository.findById(3).get();

        assertAll("Test with the data.sql",
                () -> assertThat(findId2.getIdTravel()).isEqualTo(3),
                () -> assertEquals(3, findId2.getIdArrivalFlight()),
                () -> assertEquals(3, findId2.getIdDeparture()),
                () -> assertEquals("20000.00", findId2.getPrice().toString()),
                () -> assertEquals(true, findId2.getStatus())
        );
    }



    @Test
    @DisplayName("Should save a travel in the database")
    public void save() {
        TravelEntity travelEntity = new TravelEntity.Builder()
                .setIdArrivalFlight(2)
                .setIdDeparture(2)
                .setPrice(new BigDecimal(700.00))
                .setStatus(false)
                .create();

        crudRepository.save(travelEntity);

        TravelEntity entitySaved = crudRepository.findById(5).get();

        Assertions.assertAll(
                () -> assertThat(entitySaved.getIdTravel()).isEqualTo(travelEntity.getIdTravel()),
                () -> Assertions.assertEquals(travelEntity.getIdArrivalFlight(), entitySaved.getIdArrivalFlight()),
                () -> Assertions.assertEquals(travelEntity.getIdDeparture(), entitySaved.getIdDeparture()),
                () -> assertThat(entitySaved.getPrice()).isEqualTo(travelEntity.getPrice())
        );
    }

    @Test
    @DisplayName("Should return all travels with status true in the database")
    public void findByStatus() {
        List<TravelEntity> allTravels = crudRepository.findByStatus(true);

        assertAll(
                () -> assertThat(allTravels.size()).isEqualTo(3),
                () -> assertFalse(allTravels.contains(travel)),
                () -> assertEquals(Arrays.asList(1, 2, 3), allTravels.stream().map(travel -> travel.getIdTravel()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 2, 3), allTravels.stream().map(travel -> travel.getIdArrivalFlight()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 2, 3), allTravels.stream().map(travel -> travel.getIdDeparture()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList("10000.00", "10000.00", "20000.00"), allTravels.stream().map(travel -> travel.getPrice().toString()).collect(Collectors.toList())),
                () -> assertThat(allTravels).filteredOn(
                        travels -> travels.getStatus().equals(true)
                )
        );
    }

    @Test
    @DisplayName("Should return all specific travels with a specific arrival flight id")
    public void findByIdArrivalFlight() {
        List<TravelEntity> travelsByArrivalFlight = crudRepository.findByIdArrivalFlightAndStatus(1, true).get();

        assertAll(
                () -> assertThat(travelsByArrivalFlight.size()).isEqualTo(1),
                () -> assertFalse(travelsByArrivalFlight.contains(travel)),
                () -> assertEquals(1, travelsByArrivalFlight.get(0).getIdTravel()),
                () -> assertThat(travelsByArrivalFlight).filteredOn(
                        travels -> travels.getStatus().equals(true)
                )
        );

        List<TravelEntity> travelWithoutArrivalFlight = crudRepository.findByIdArrivalFlightAndStatus(12, true).get();

        assertTrue(travelWithoutArrivalFlight.isEmpty());
    }

    @Test
    @DisplayName("Should return all specific travels with a specific departure id")
    public void findByIdDeparture() {
        List<TravelEntity> travelsByDeparture = crudRepository.findByIdDepartureAndStatus(2, true).get();

        assertAll(
                () -> assertThat(travelsByDeparture.size()).isEqualTo(1),
                () -> assertFalse(travelsByDeparture.contains(travel)),
                () -> assertEquals(2, travelsByDeparture.get(0).getIdTravel()),
                () -> assertThat(travelsByDeparture).filteredOn(
                        travels -> travels.getStatus().equals(true)
                )
        );

        List<TravelEntity> travelWithoutDeparture = crudRepository.findByIdDepartureAndStatus(8, true).get();

        assertTrue(travelWithoutDeparture.isEmpty());

    }

    @Test
    @DisplayName("Should update all travels in the database where arrivalEntities are false")
    public void updateTravelStatus() {
        arrivalCrudRepository.updateArrivalStatus(LocalDateTime.now());

        List<Integer> idArrival = arrivalCrudRepository.findByStatus(false).stream().map(arrival -> arrival.getIdArrivalFlight()).collect(Collectors.toList());

        for (Integer id: idArrival) {
            crudRepository.updateTravelStatus(id);
        }

        List<TravelEntity> allTravels = crudRepository.findByStatus(true);

        assertAll(
                () -> assertThat(allTravels.size()).isEqualTo(2),
                () -> assertFalse(allTravels.contains(travel)),
                () -> assertEquals(Arrays.asList(2, 3), allTravels.stream().map(travel -> travel.getIdTravel()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(2, 3), allTravels.stream().map(travel -> travel.getIdArrivalFlight()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(2, 3), allTravels.stream().map(travel -> travel.getIdDeparture()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList("10000.00", "20000.00"), allTravels.stream().map(travel -> travel.getPrice().toString()).collect(Collectors.toList())),
                () -> assertThat(allTravels).filteredOn(
                        travels -> travels.getStatus().equals(true)
                )
        );
    }
}