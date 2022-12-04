package com.practice.flightbooking.controller;

import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.domain.service.AirportService;
import com.practice.flightbooking.persistence.crud.AirportCrudRepository;
import com.practice.flightbooking.persistence.mapper.AirportMapper;
import com.practice.flightbooking.web.controller.AirportController;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("dev")
@WebMvcTest(AirportController.class)
class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    private List<Airport> airportsList;

    @BeforeEach
    void setUp() {
        Airport airport1 = Airport.builder()
                .setAirportId(1)
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

        airportsList = Arrays.asList(airport1, airport2);
    }

    @Test
    @DisplayName("Should return an airport in json format with a specific id using the service or return a not found")
    void getById() throws Exception {
        Mockito.when(airportService.getById(1))
                .thenReturn(Optional.of(airportsList.get(0)));

        assertAll(
                () -> mockMvc.perform(get("/places/airport/1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.airportId")
                                .value(airportsList.get(0).getAirportId()))
                        .andExpect(jsonPath("$.country")
                                .value(airportsList.get(0).getCountry()))
                        .andExpect(jsonPath("$.state")
                                .value(airportsList.get(0).getState()))
                        .andExpect(jsonPath("$.city")
                                .value(airportsList.get(0).getCity()))
                        .andExpect(jsonPath("$.iata")
                                .value(airportsList.get(0).getIata())),

                () -> mockMvc.perform(get("/places/airport/54")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return all airportIds in json format that match with a specific country using the service or return a not found")
    void getByCountry() throws Exception {
        Mockito.when(airportService.getByCountry("Mexico"))
                .thenReturn(Optional.of(airportsList));

        assertAll(
                () -> mockMvc.perform(get("/places/country/Mexico")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(2)))
                        .andExpect(jsonPath("$[0]").value(1))
                        .andExpect(jsonPath("$[1]").value(322)),

                () -> mockMvc.perform(get("/places/country/564")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return all airportIds in json format that match with a specific state using the service or return a not found")
    void getByState() throws Exception {
        Mockito.when(airportService.getByState("Chiapas"))
                .thenReturn(Optional.of(Arrays.asList(airportsList.get(1))));

        assertAll(
                () -> mockMvc.perform(get("/places/state/Chiapas")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(1)))
                        .andExpect(jsonPath("$[0]").value(322)),

                () -> mockMvc.perform(get("/places/state/geshgr")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return all airportIds in json format that match with a specific city using the service or return a not found")
    void getByCity() throws Exception {
        Mockito.when(airportService.getByCity("Hermosillo"))
                .thenReturn(Optional.of(Arrays.asList(airportsList.get(0))));

        assertAll(
                () -> mockMvc.perform(get("/places/city/Hermosillo")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.*", hasSize(1)))
                        .andExpect(jsonPath("$[0]").value(1)),

                () -> mockMvc.perform(get("/places/city/WAFGH")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }
}