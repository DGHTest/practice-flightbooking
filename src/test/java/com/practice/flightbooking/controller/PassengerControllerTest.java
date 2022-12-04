package com.practice.flightbooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.flightbooking.domain.Departure;
import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.domain.service.DepartureService;
import com.practice.flightbooking.domain.service.PassengerService;
import com.practice.flightbooking.web.controller.DepartureController;
import com.practice.flightbooking.web.controller.PassengerController;
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

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@WebMvcTest(PassengerController.class)
class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    private List<Passenger> passengerList;

    @BeforeEach
    void setUp() {
        Passenger passenger1 = Passenger.builder()
                .setPassengerId(1)
                .setLastNames("Ramirez Flores")
                .setFirstName("Jose")
                .setBirthDate(LocalDate.of(1970, 5, 8))
                .setEmail("joseramirez1@random.names")
                .setTelephoneNumber("5550657078")
                .setCountry("Mexico")
                .setState("Coahuila")
                .setCity("Torreon")
                .setPassportNumber(6549647689L)
                .setExpirationDate(LocalDate.of(2024, 11, 28))
                .setNationality("MEX")
                .create();

        Passenger passenger2 = Passenger.builder()
                .setPassengerId(2)
                .setLastNames("Hernandez Sanchez")
                .setFirstName("Maria")
                .setBirthDate(LocalDate.of(1999, Month.SEPTEMBER, 25))
                .setEmail("mariahernandez1@random.names")
                .setTelephoneNumber("5546460968")
                .setCountry("Mexico")
                .setState("Nuevo Leon")
                .setCity("Monterrey")
                .setPassportNumber(3453476534L)
                .setExpirationDate(LocalDate.of(2026, Month.NOVEMBER, 12))
                .setNationality("MEX")
                .create();

        Passenger passenger3 = Passenger.builder()
                .setPassengerId(3)
                .setLastNames("Default is my last name")
                .setFirstName("Default")
                .setBirthDate(LocalDate.of(3000, 01, 01))
                .setEmail("default@default.com")
                .setTelephoneNumber("000000000000")
                .setCountry("Default Country")
                .setState("Default State")
                .setCity("Default City")
                .setPassportNumber(0000000000L)
                .setExpirationDate(LocalDate.of(2000, 01, 01))
                .setNationality("DFT")
                .create();

        passengerList = Arrays.asList(passenger1, passenger2, passenger3);
    }

    @Test
    @DisplayName("Should return an passenger in json format with a specific id using the service or return a not found")
    void getPassengerById() throws Exception {
        Mockito.when(passengerService.getPassengerById(2))
                .thenReturn(Optional.of(passengerList.get(1)));

        assertAll(
                () -> mockMvc.perform(get("/passengers/2")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.passengerId")
                                .value(passengerList.get(1).getPassengerId()))
                        .andExpect(jsonPath("$.lastNames")
                                .value(passengerList.get(1).getLastNames()))
                        .andExpect(jsonPath("$.firstName")
                                .value(passengerList.get(1).getFirstName()))
                        .andExpect(jsonPath("$.birthDate")
                                .value(passengerList.get(1).getBirthDate().toString()))
                        .andExpect(jsonPath("$.email")
                                .value(passengerList.get(1).getEmail()))
                        .andExpect(jsonPath("$.telephoneNumber")
                                .value(passengerList.get(1).getTelephoneNumber()))
                        .andExpect(jsonPath("$.country")
                                .value(passengerList.get(1).getCountry()))
                        .andExpect(jsonPath("$.state")
                                .value(passengerList.get(1).getState()))
                        .andExpect(jsonPath("$.city")
                                .value(passengerList.get(1).getCity()))
                        .andExpect(jsonPath("$.passportNumber")
                                .value(passengerList.get(1).getPassportNumber()))
                        .andExpect(jsonPath("$.expirationDate")
                                .value(passengerList.get(1).getExpirationDate().toString()))
                        .andExpect(jsonPath("$.nationality")
                                .value(passengerList.get(1).getNationality()))
                        .andExpect(jsonPath("$.passengersTravel")
                                .value(passengerList.get(1).getPassengersTravel())),

                () -> mockMvc.perform(get("/passengers/10")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return an passenger in json format with a specific email using the service or return a not found")
    void getPassengerByEmail() {
        Mockito.when(passengerService.getPassengerByEmail("joseramirez1@random.names"))
                .thenReturn(Optional.of(passengerList.get(0)));

        assertAll(
                () -> mockMvc.perform(get("/passengers/email/joseramirez1@random.names")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.passengerId")
                                .value(passengerList.get(0).getPassengerId()))
                        .andExpect(jsonPath("$.email")
                                .value(passengerList.get(0).getEmail())),

                () -> mockMvc.perform(get("/passengers/email/mariahernandez1@random.names")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should save one passenger in json format using the service or return a bad request")
    void savePassenger() throws Exception {
        Passenger passengerSave = Passenger.builder()
                .setPassengerId(32)
                .setLastNames("Hernandez Juarez")
                .setFirstName("Lopez")
                .setBirthDate(LocalDate.of(1999, Month.SEPTEMBER, 25))
                .setEmail("hernandez1@random.names")
                .setTelephoneNumber("5546460968")
                .setCountry("Mexico")
                .setState("Nuevo Leon")
                .setCity("Monterrey")
                .setPassportNumber(3453474444L)
                .setExpirationDate(LocalDate.of(2026, Month.NOVEMBER, 12))
                .setNationality("MEX")
                .setPassengersTravel(null)
                .create();

        Mockito.when(passengerService.savePassenger(ArgumentMatchers.any()))
                .thenReturn(passengerSave);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc.perform(MockMvcRequestBuilders.post("/passengers/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passengerSave)))
                        /*.content("{\"passengerId\":32,\"lastNames\":\"Hernandez Juarez\",\"firstName\":\"Lopez\",\"birthDate\":\"1999-09-25\"" +
                                ",\"email\":\"hernandez1@random.names\",\"telephoneNumber\":\"5546460968\",\"country\":\"Mexico\"" +
                                ",\"state\":\"Nuevo Leon\",\"city\":\"Monterrey\",\"passportNumber\":3453474444,\"expirationDate\":\"2026-11-12\"" +
                                ",\"nationality\":\"MEX\",\"passengersTravel\":null}"))*/

                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return ok or not found")
    void updatePassengerStatusById() {
        Mockito.when(passengerService.updatePassengerStatusById(true, 2))
                .thenReturn(true);

        assertAll(
                () -> mockMvc.perform(put("/passengers/update-status/2/true"))
                        .andExpect(status().isOk()),
                () -> mockMvc.perform(put("/passengers/update-status/4/false"))
                        .andExpect(status().isNotFound())
        );
    }

    @Test
    @DisplayName("Should return ok or not found")
    void deletePassenger() {
        Mockito.when(passengerService.deletePassenger(654))
                .thenReturn(true);

        assertAll(
                () -> mockMvc.perform(delete("/passengers/delete/654"))
                        .andExpect(status().isOk()),
                () -> mockMvc.perform(delete("/passengers/delete/442"))
                        .andExpect(status().isNotFound())
        );
    }
}