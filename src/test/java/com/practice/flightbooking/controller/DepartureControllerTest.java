package com.practice.flightbooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.Departure;
import com.practice.flightbooking.domain.service.ArrivalFlightService;
import com.practice.flightbooking.domain.service.DepartureService;
import com.practice.flightbooking.web.controller.ArrivalFlightController;
import com.practice.flightbooking.web.controller.DepartureController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@WebMvcTest(DepartureController.class)
class DepartureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartureService departureService;

    private List<Departure> departureList;

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

        departureList = Arrays.asList(departure1, departure2, departure3);
    }

    @Test
    @DisplayName("Should return all departures in json format using the service")
    void getAllDepartures() throws Exception {
        Mockito.when(departureService.getAllDepartures())
                .thenReturn(departureList);

        mockMvc.perform(get("/departures")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[0].departureId").value(23))
                .andExpect(jsonPath("$[1].departureId").value(33))
                .andExpect(jsonPath("$[2].departureId").value(433));
    }

    @Test
    @DisplayName("Should return an departure in json format with a specific id using the service or return a not found")
    void getDepartureById() throws Exception {
        Mockito.when(departureService.getDepartureById(433))
                .thenReturn(Optional.of(departureList.get(2)));

        assertAll(
                () -> mockMvc.perform(get("/departures/433")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departureId")
                        .value(departureList.get(2).getDepartureId()))
                .andExpect(jsonPath("$.airportId")
                        .value(departureList.get(2).getAirportId()))
                .andExpect(jsonPath("$.departureTime")
                        .value(departureList.get(2).getDepartureTime().toString())),

                () -> mockMvc.perform(get("/departures/2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return all departures in json format with a specific airportId using the service or return a not found")
    void getByIdAirport() throws Exception {
        Mockito.when(departureService.getByIdAirport(343))
                .thenReturn(Optional.of(Arrays.asList(departureList.get(1))));

        assertAll(
                () -> mockMvc.perform(get("/departures/airport/343")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(1)))
                        .andExpect(jsonPath("$[0].departureId")
                                .value(departureList.get(1).getDepartureId()))
                        .andExpect(jsonPath("$[0].airportId")
                                .value(departureList.get(1).getAirportId())),

                () -> mockMvc.perform(get("/departures/airport/33")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );

    }

    @Test
    @DisplayName("Should return all departures in json format with departureTime after given time using the service or return a not found")
    void getByDepartureTime() throws Exception {
        LocalDateTime time = LocalDateTime.of(2022,11,30,11, 19,43);

        Mockito.when(departureService.getByDepartureTime(time))
                .thenReturn(Optional.of(departureList));

        assertAll(
                () -> mockMvc.perform(get("/departures/time/" + time)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(3)))
                        .andExpect(jsonPath("$[0].departureId").value(23))
                        .andExpect(jsonPath("$[0].departureTime")
                                .value(departureList.get(1).getDepartureTime().toString()))
                        .andExpect(jsonPath("$[1].departureId").value(33))
                        .andExpect(jsonPath("$[1].departureTime")
                                .value(departureList.get(1).getDepartureTime().toString()))
                        .andExpect(jsonPath("$[2].departureId").value(433))
                        .andExpect(jsonPath("$[2].departureTime")
                                .value(departureList.get(1).getDepartureTime().toString())),

                () -> mockMvc.perform(get("/departures/time/" + LocalDateTime.of(2024,11,30,11, 19,43))
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );

    }

    @Test
    @DisplayName("Should save one departure in json format using the service or return a bad request")
    void saveDeparture() throws Exception {
        Departure departureSave = Departure.builder()
                .setDepartureId(34)
                .setAirportId(254)
                .setDepartureTime(LocalDateTime.of(2024, Month.DECEMBER, 12, 15, 32, 54))
                .setAirport(null)
                .create();

        Mockito.when(departureService.saveDeparture(ArgumentMatchers.any()))
                .thenReturn(departureSave);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc.perform(MockMvcRequestBuilders.post("/departures/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departureSave)))
                        //.content("{\"arrivalFlightId\":34,\"airportId\":254,\"arrivalTime\":\"2024-12-09T15:32:54\",\"airport\":null}"))
                .andExpect(status().isCreated());
    }
}