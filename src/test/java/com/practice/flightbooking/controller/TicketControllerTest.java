package com.practice.flightbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.flightbooking.domain.ArrivalFlight;
import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.domain.service.ArrivalFlightService;
import com.practice.flightbooking.domain.service.TicketService;
import com.practice.flightbooking.web.controller.ArrivalFlightController;
import com.practice.flightbooking.web.controller.TicketController;
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
@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    private List<Ticket> ticketList;

    @BeforeEach
    void setUp() {
        Ticket ticket1 = Ticket.builder()
                .setTicketId(1)
                .setPassengerId(4)
                .setTravelId(32)
                .setBoardingTime(LocalDateTime.of(2024, Month.JANUARY, 12, 13, 34, 13))
                .create();

        Ticket ticket2 = Ticket.builder()
                .setTicketId(2)
                .setPassengerId(8)
                .setTravelId(72)
                .setBoardingTime(LocalDateTime.of(2024, Month.JULY, 12, 13, 34, 12))
                .create();

        Ticket ticket3 = Ticket.builder()
                .setTicketId(3)
                .setPassengerId(8)
                .setTravelId(90)
                .setBoardingTime(LocalDateTime.of(2024, Month.SEPTEMBER, 12, 13, 34, 54))
                .create();

        ticketList = Arrays.asList(ticket1, ticket2, ticket3);
    }

    @Test
    @DisplayName("Should return an ticket in json format with a specific id using the service or return a not found")
    void getTicketById() {
        Mockito.when(ticketService.getTicketById(2))
                .thenReturn(Optional.of(ticketList.get(1)));

        assertAll(
                () -> mockMvc.perform(get("/tickets/2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.ticketId")
                                .value(ticketList.get(1).getTicketId()))
                        .andExpect(jsonPath("$.passengerId")
                                .value(ticketList.get(1).getPassengerId()))
                        .andExpect(jsonPath("$.travelId")
                                .value(ticketList.get(1).getTravelId()))
                        .andExpect(jsonPath("$.boardingTime")
                                .value(ticketList.get(1).getBoardingTime().toString()))
                        .andExpect(jsonPath("$.travel")
                                .value(ticketList.get(1).getTravel())),

                () -> mockMvc.perform(get("/tickets/54")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return all tickets in json format with a specific passengerId using the service or return a not found")
    void getByIdPassenger() {
        Mockito.when(ticketService.getByIdPassenger(8))
                .thenReturn(Optional.of(Arrays.asList(ticketList.get(1), ticketList.get(2))));

        assertAll(
                () -> mockMvc.perform(get("/tickets/passenger/8")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(2)))
                        .andExpect(jsonPath("$[0].ticketId")
                                .value(ticketList.get(1).getTicketId()))
                        .andExpect(jsonPath("$[0].passengerId")
                                .value(ticketList.get(1).getPassengerId()))
                        .andExpect(jsonPath("$[0].travelId")
                                .value(ticketList.get(1).getTravelId()))
                        .andExpect(jsonPath("$[0].boardingTime")
                                .value(ticketList.get(1).getBoardingTime().toString()))
                        .andExpect(jsonPath("$[0].travel")
                                .value(ticketList.get(1).getTravel()))

                        .andExpect(jsonPath("$[1].ticketId")
                                .value(ticketList.get(2).getTicketId()))
                        .andExpect(jsonPath("$[1].passengerId")
                                .value(ticketList.get(2).getPassengerId()))
                        .andExpect(jsonPath("$[1].travelId")
                                .value(ticketList.get(2).getTravelId()))
                        .andExpect(jsonPath("$[1].boardingTime")
                                .value(ticketList.get(2).getBoardingTime().toString()))
                        .andExpect(jsonPath("$[1].travel")
                                .value(ticketList.get(2).getTravel())),

                () -> mockMvc.perform(get("/tickets/passenger/54")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should save one ticket in json format using the service or return a bad request")
    void saveTicket() throws Exception {
        Ticket ticketSave = Ticket.builder()
                .setTicketId(54)
                .setPassengerId(32)
                .setTravelId(432)
                .setBoardingTime(LocalDateTime.of(2054, Month.DECEMBER, 23, 13, 34, 54))
                .create();

        Mockito.when(ticketService.saveTicket(ArgumentMatchers.any()))
                .thenReturn(ticketSave);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc.perform(MockMvcRequestBuilders.post("/tickets/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticketSave)))
                .andExpect(status().isCreated());
    }
}