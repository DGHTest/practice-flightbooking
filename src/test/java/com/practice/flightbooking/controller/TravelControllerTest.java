package com.practice.flightbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.domain.Travel;
import com.practice.flightbooking.domain.service.TicketService;
import com.practice.flightbooking.domain.service.TravelService;
import com.practice.flightbooking.web.controller.TicketController;
import com.practice.flightbooking.web.controller.TravelController;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@WebMvcTest(TravelController.class)
class TravelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelService travelService;

    private List<Travel> travelList;

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

        travelList = Arrays.asList(travel1, travel2, travel3);
    }

    @Test
    @DisplayName("Should return all travels in json format using the service")
    void getAllTravels() throws Exception {
        Mockito.when(travelService.getAllTravels())
                .thenReturn(travelList);

        mockMvc.perform(get("/travels")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[0].travelId").value(1))
                .andExpect(jsonPath("$[1].travelId").value(2))
                .andExpect(jsonPath("$[2].travelId").value(3));
    }

    @Test
    @DisplayName("Should return an travel in json format with a specific id using the service or return a not found")
    void getTravelById() {
        Mockito.when(travelService.getTravelById(1))
                .thenReturn(Optional.of(travelList.get(2)));

        assertAll(
                () -> mockMvc.perform(get("/travels/1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.travelId")
                                .value(travelList.get(2).getTravelId()))
                        .andExpect(jsonPath("$.arrivalFlightId")
                                .value(travelList.get(2).getArrivalFlightId()))
                        .andExpect(jsonPath("$.departureId")
                                .value(travelList.get(2).getDepartureId()))
                        .andExpect(jsonPath("$.price")
                                .value(travelList.get(2).getPrice()))
                        .andExpect(jsonPath("$.arrivalFlight")
                                .value(travelList.get(2).getArrivalFlight()))
                        .andExpect(jsonPath("$.departure")
                                .value(travelList.get(2).getDeparture())),

                () -> mockMvc.perform(get("/travels/432")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return all travels in json format with a specific arrivalId using the service or return a not found")
    void getByIdArrivalFlight() {
        Mockito.when(travelService.getByIdArrivalFlight(6))
                .thenReturn(Optional.of(Arrays.asList(travelList.get(1), travelList.get(2))));

        assertAll(
                () -> mockMvc.perform(get("/travels/arrival/6")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(2)))
                        .andExpect(jsonPath("$[0].travelId")
                                .value(travelList.get(1).getTravelId()))
                        .andExpect(jsonPath("$[0].arrivalFlightId")
                                .value(travelList.get(1).getArrivalFlightId()))
                        .andExpect(jsonPath("$[1].travelId")
                                .value(travelList.get(2).getTravelId()))
                        .andExpect(jsonPath("$[1].arrivalFlightId")
                                .value(travelList.get(2).getArrivalFlightId())),

                () -> mockMvc.perform(get("/travels/arrival/432")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return all travels in json format with a specific departureId using the service or return a not found")
    void getByIdDeparture() {
        Mockito.when(travelService.getByIdDeparture(4))
                .thenReturn(Optional.of(Arrays.asList(travelList.get(0), travelList.get(1))));

        assertAll(
                () -> mockMvc.perform(get("/travels/departure/4")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(2)))
                        .andExpect(jsonPath("$[0].travelId")
                                .value(travelList.get(0).getTravelId()))
                        .andExpect(jsonPath("$[0].arrivalFlightId")
                                .value(travelList.get(0).getArrivalFlightId()))
                        .andExpect(jsonPath("$[1].travelId")
                                .value(travelList.get(1).getTravelId()))
                        .andExpect(jsonPath("$[1].arrivalFlightId")
                                .value(travelList.get(1).getArrivalFlightId())),

                () -> mockMvc.perform(get("/travels/departure/432")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should save one travel in json format using the service or return a bad request")
    void saveTravel() throws Exception {
        Travel travelSave = Travel.builder()
                .setTravelId(654)
                .setArrivalFlightId(432)
                .setDepartureId(65467)
                .setPrice(BigDecimal.valueOf(5778400.00))
                .create();

        Mockito.when(travelService.saveTravel(ArgumentMatchers.any()))
                .thenReturn(travelSave);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/travels/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(travelSave)))
                .andExpect(status().isCreated());
    }
}