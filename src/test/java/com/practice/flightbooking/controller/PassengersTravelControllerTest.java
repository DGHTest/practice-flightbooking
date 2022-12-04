package com.practice.flightbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.flightbooking.domain.PassengersTravel;
import com.practice.flightbooking.domain.service.PassengerService;
import com.practice.flightbooking.domain.service.PassengersTravelService;
import com.practice.flightbooking.web.controller.PassengerController;
import com.practice.flightbooking.web.controller.PassengersTravelController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@WebMvcTest(PassengersTravelController.class)
class PassengersTravelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengersTravelService passengersTravelService;

    @Test
    @DisplayName("Should return all travelId in json format with a specific passengerId using the service or return a not found")
    void getPassengersTravelByIdPassenger() {
        PassengersTravel pT1 = PassengersTravel.builder().setTravelId(32).create();
        PassengersTravel pT2 = PassengersTravel.builder().setTravelId(543).create();
        PassengersTravel pT3 = PassengersTravel.builder().setTravelId(654).create();
        PassengersTravel pT4 = PassengersTravel.builder().setTravelId(34).create();
        PassengersTravel pT5 = PassengersTravel.builder().setTravelId(2).create();



        Mockito.when(passengersTravelService.getPassengersTravelByIdPassenger(65))
                .thenReturn(Optional.of(Arrays.asList(pT1, pT2, pT3, pT4, pT5)));

        assertAll(
                () -> mockMvc.perform(get("/passenger-travels/65")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(5)))
                        .andExpect(jsonPath("$[0].travelId")
                                .value(pT1.getTravelId()))
                        .andExpect(jsonPath("$[1].travelId")
                                .value(pT2.getTravelId()))
                        .andExpect(jsonPath("$[2].travelId")
                                .value(pT3.getTravelId()))
                        .andExpect(jsonPath("$[3].travelId")
                                .value(pT4.getTravelId()))
                        .andExpect(jsonPath("$[4].travelId")
                                .value(pT5.getTravelId())),

                () -> mockMvc.perform(get("/passenger-travels/32")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should save two ids in the url using the service or return a bad request")
    void savePassengersTravel() throws Exception {
        PassengersTravel idReturned = PassengersTravel.builder().setTravelId(654).create();

        Mockito.when(passengersTravelService.savePassengersTravel(654, 65))
                .thenReturn(idReturned);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/passenger-travels/save/654-65")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(idReturned)))
                        .andExpect(status().isCreated());
    }
}