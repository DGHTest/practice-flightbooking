package com.practice.flightbooking.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.service.AirportService;
import com.practice.flightbooking.domain.service.ArrivalFlightService;
import com.practice.flightbooking.web.controller.AirportController;
import com.practice.flightbooking.web.controller.ArrivalFlightController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
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
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@WebMvcTest(ArrivalFlightController.class)
class ArrivalFlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArrivalFlightService arrivalFlightService;

    private List<ArrivalFlight> arrivalFlightList;

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

        arrivalFlightList = Arrays.asList(arrivalFlight1, arrivalFlight2, arrivalFlight3);
    }

    @Test
    @DisplayName("Should return all arrivalFlights in json format using the service")
    void getAllArrivalFlights() throws Exception {
        Mockito.when(arrivalFlightService.getAllArrivalFlights())
                .thenReturn(arrivalFlightList);

        mockMvc.perform(get("/arrivals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[0].arrivalFlightId").value(23))
                .andExpect(jsonPath("$[1].arrivalFlightId").value(33))
                .andExpect(jsonPath("$[2].arrivalFlightId").value(433));
    }

    @Test
    @DisplayName("Should return an arrivalFlight in json format with a specific id using the service or return a not found")
    void getArrivalById() throws Exception {
        Mockito.when(arrivalFlightService.getArrivalById(33))
                .thenReturn(Optional.of(arrivalFlightList.get(1)));

        assertAll(
                () -> mockMvc.perform(get("/arrivals/33")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.arrivalFlightId")
                                .value(arrivalFlightList.get(1).getArrivalFlightId()))
                        .andExpect(jsonPath("$.airportId")
                                .value(arrivalFlightList.get(1).getAirportId()))
                        .andExpect(jsonPath("$.arrivalTime")
                                .value(arrivalFlightList.get(1).getArrivalTime().toString())),

                () -> mockMvc.perform(get("/arrivals/54")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return all arrivalFlights in json format with a specific airportId using the service or return a not found")
    void getByIdAirport() throws Exception {
        Mockito.when(arrivalFlightService.getByIdAirport(213))
                .thenReturn(Optional.of(Arrays.asList(arrivalFlightList.get(0), arrivalFlightList.get(2))));

        assertAll(
                () -> mockMvc.perform(get("/arrivals/airport/213")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(2)))
                        .andExpect(jsonPath("$[0].arrivalFlightId")
                                .value(arrivalFlightList.get(0).getArrivalFlightId()))
                        .andExpect(jsonPath("$[1].arrivalFlightId")
                                .value(arrivalFlightList.get(2).getArrivalFlightId()))
                        .andExpect(jsonPath("$[0].airportId")
                                .value(arrivalFlightList.get(0).getAirportId()))
                        .andExpect(jsonPath("$[1].airportId")
                                .value(arrivalFlightList.get(2).getAirportId())),

                () -> mockMvc.perform(get("/arrivals/airport/43")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return all arrivalFlights in json format with arrivalTime after given time using the service or return a not found")
    void getByArrivalTime() throws Exception {
        LocalDateTime time = LocalDateTime.of(2022,11,30,11, 19,43);

        Mockito.when(arrivalFlightService.getByArrivalTime(time))
                .thenReturn(Optional.of(arrivalFlightList));

        assertAll(
                () -> mockMvc.perform(get("/arrivals/time/" + time)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(3)))
                        .andExpect(jsonPath("$[0].arrivalFlightId").value(23))
                        .andExpect(jsonPath("$[0].arrivalTime")
                                .value(arrivalFlightList.get(1).getArrivalTime().toString()))
                        .andExpect(jsonPath("$[1].arrivalFlightId").value(33))
                        .andExpect(jsonPath("$[1].arrivalTime")
                                .value(arrivalFlightList.get(1).getArrivalTime().toString()))
                        .andExpect(jsonPath("$[2].arrivalFlightId").value(433))
                        .andExpect(jsonPath("$[2].arrivalTime")
                                .value(arrivalFlightList.get(1).getArrivalTime().toString())),

                () -> mockMvc.perform(get("/arrivals/time/" + LocalDateTime.of(2024,11,30,11, 19,43))
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should save one arrivalFlight in json format using the service or return a bad request")
    void saveArrival() throws Exception {
        ArrivalFlight arrivalFlightSave = ArrivalFlight.builder()
                .setArrivalFlightId(1)
                .setAirportId(21)
                .setArrivalTime(LocalDateTime.of(2024, Month.DECEMBER, 12, 15, 32, 54))
                .setAirport(null)
                .create();

        Mockito.when(arrivalFlightService.saveArrival(ArgumentMatchers.any()))
                .thenReturn(arrivalFlightSave);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc.perform(MockMvcRequestBuilders.post("/arrivals/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(arrivalFlightSave)))
                //        .content("{\"arrivalFlightId\":1,\"airportId\":21,\"arrivalTime\":\"2024-12-09T15:32:54\",\"airport\":null}"))
                .andExpect(status().isCreated());
    }
}