package com.practice.flightbooking.repository;

import com.practice.flightbooking.domain.repository.AirportRepository;
import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.persistence.crud.AirportCrudRepository;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import com.practice.flightbooking.persistence.mapper.AirportMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirportRepositoryImplTest {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportMapper airportMapper;

    @MockBean
    private AirportCrudRepository airportCrudRepository;

    private  Optional<List<AirportEntity>> airportList1;
    private  Optional<List<AirportEntity>> airportList2;
    private Optional<List<AirportEntity>> airportList;


    @BeforeEach
    void setUp() {
        AirportEntity airportEntity1 = AirportEntity.builder()
                .setIdAirport(3)
                .setName("General Mariano Escobedo International")
                .setCountry("Mexico")
                .setState("Nuevo Leon")
                .setCity("Monterrey")
                .setIata("MTY")
                .setIcao("MMMY")
                .create();

        AirportEntity airportEntity2 = AirportEntity.builder()
                .setIdAirport(4)
                .setName("Aeropuerto Internacional Plan de Guadalupe")
                .setCountry("Mexico")
                .setState("Coahuila")
                .setCity("Ramos Arizpe")
                .setIata("SLW")
                .setIcao("MMIO")
                .create();

        airportList1 = Optional.of(Arrays.asList(airportEntity1));
        airportList2 = Optional.of(Arrays.asList(airportEntity2));

        airportList = Optional.of(Arrays.asList(airportEntity1, airportEntity2));
    }

    @Test
    @DisplayName("Should search an airportEntity that match with a specific id and then with the mapper transform to airport")
    void getById() throws Exception {
        Optional<AirportEntity> findWithId = Optional.of(AirportEntity.builder()
                .setIdAirport(2)
                .setName("Dallas Fort Worth International Airport")
                .setCountry("United States")
                .setState("Texas")
                .setCity("Dallas-Fort Worth")
                .setIata("DFW")
                .setIcao("KDFW")
                .create());

        Mockito.when(airportCrudRepository.findById(2))
                .thenReturn(findWithId);

        Airport airportById = airportRepository.getById(2);

        Exception exception1 = assertThrows(Exception.class, () -> airportRepository.getById(1));
        Exception exception2 = assertThrows(Exception.class, () -> Integer.parseInt("id "));

        String expectedMessage = "Airports by id not found";

        assertAll(
                () -> assertEquals(expectedMessage, exception1.getMessage()),
                () -> assertNotEquals(expectedMessage, exception2.getMessage()),
                () -> assertEquals(2, airportById.getAirportId())
        );
    }

    @Test
    @DisplayName("Should search all airportsEntities that match with a specific country and then with the mapper transform to airport")
    void getByCountry() throws Exception {
        Mockito.when(airportCrudRepository.findByCountry("Mexico"))
                .thenReturn(airportList);

        List<Airport> airportsByCountry = airportRepository.getByCountry("Mexico");

        Exception exception1 = assertThrows(Exception.class, () -> airportRepository.getByCountry(""));
        Exception exception2 = assertThrows(Exception.class, () -> Integer.parseInt("Country "));

        String expectedMessage = "Airports by country not found";

        assertAll(
                () -> assertEquals(expectedMessage, exception1.getMessage()),
                () -> assertNotEquals(expectedMessage, exception2.getMessage()),
                () -> Assertions.assertThat(airportsByCountry.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(3, 4), airportsByCountry.stream().map(airport -> airport.getAirportId()).collect(Collectors.toList())),
                () -> assertTrue(airportsByCountry.stream().map(airport -> airport.getCountry()).allMatch(country -> country == "Mexico"))
        );
    }

    @Test
    @DisplayName("Should search all airportsEntities that match with a specific state and then with the mapper transform to airport")
    void getByState() throws Exception {
        Mockito.when(airportCrudRepository.findByState("Nuevo Leon"))
                .thenReturn(airportList1);
        Mockito.when(airportCrudRepository.findByState("Coahuila"))
                .thenReturn(airportList2);

        List<Airport> airportsByState = airportRepository.getByState("Nuevo Leon");
        airportsByState.add(airportRepository.getByState("Coahuila").get(0));

        Exception exception1 = assertThrows(Exception.class, () -> airportRepository.getByState(""));
        Exception exception2 = assertThrows(Exception.class, () -> airportRepository.getByState("Leon"));

        String expectedMessage = "Airports by state not found";

        assertAll(
                () -> assertEquals(expectedMessage, exception1.getMessage()),
                () -> assertEquals(expectedMessage, exception2.getMessage()),
                () -> assertEquals(Arrays.asList(3, 4), airportsByState.stream().map(airport -> airport.getAirportId()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList("Nuevo Leon", "Coahuila"), airportsByState.stream().map(airport -> airport.getState()).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should search all airportsEntities that match with a specific city and then with the mapper transform to airport")
    void getByCity() throws Exception {
        Mockito.when(airportCrudRepository.findByCity("Monterrey"))
                .thenReturn(airportList1);
        Mockito.when(airportCrudRepository.findByCity("Ramos Arizpe"))
                .thenReturn(airportList2);

        List<Airport> airportsByCity = airportRepository.getByCity("Monterrey");
        airportsByCity.add(airportRepository.getByCity("Ramos Arizpe").get(0));

        Exception exception1 = assertThrows(Exception.class, () -> airportRepository.getByCity(""));
        Exception exception2 = assertThrows(Exception.class, () -> airportRepository.getByCity("Ramos"));

        String expectedMessage = "Airports by city not found";

        assertAll(
                () -> assertEquals(expectedMessage, exception1.getMessage()),
                () -> assertEquals(expectedMessage, exception2.getMessage()),
                () -> assertEquals(Arrays.asList(3, 4), airportsByCity.stream().map(airport -> airport.getAirportId()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList("Monterrey", "Ramos Arizpe"), airportsByCity.stream().map(airport -> airport.getCity()).collect(Collectors.toList()))
        );
    }
}