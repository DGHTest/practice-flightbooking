package com.practice.flightbooking.service;

import com.practice.flightbooking.domain.PassengersTravel;
import com.practice.flightbooking.domain.repository.PassengersTravelRepository;
import com.practice.flightbooking.domain.service.PassengersTravelService;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntityPk;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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
class PassengersTravelServiceTest {

    @Autowired
    private PassengersTravelService passengersTravelService;

    @MockBean
    private PassengersTravelRepository passengersTravelRepository;

    @Test
    @DisplayName("Should return all passengersTravels with the specific passengerId using the repository")
    void getPassengersTravelByIdPassenger() throws Exception {
        PassengersTravel passengersTravelsEntity1 = PassengersTravel.builder()
                .setTravelId(33).create();

        PassengersTravel passengersTravelsEntity2 = PassengersTravel.builder()
                .setTravelId(44).create();

        Mockito.when(passengersTravelRepository.getPassengersTravelByIdPassenger(2))
                .thenReturn(Arrays.asList(passengersTravelsEntity1, passengersTravelsEntity2));

        List<PassengersTravel> passengersTravels = passengersTravelService.getPassengersTravelByIdPassenger(2);

        assertAll(
                () -> Assertions.assertThat(passengersTravels.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(33, 44), passengersTravels.stream().map(PassengersTravel::getTravelId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should transform two specific id to passengersTravelEntity and save it and then return it with the mapper to passengersTravel or throw an error if the id already exist")
    void savePassengersTravel() throws Exception {
        PassengersTravel passengersTravel = PassengersTravel.builder()
                .setTravelId(11).create();

        Mockito.when(passengersTravelRepository.savePassengersTravel(11, 213)).thenReturn(passengersTravel);

        PassengersTravel passengersTravelSave = passengersTravelService.savePassengersTravel(11, 213);

        assertAll(
                () -> assertEquals(11, passengersTravelSave.getTravelId())
        );
    }
}