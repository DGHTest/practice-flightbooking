package com.practice.flightbooking.repository;

import com.practice.flightbooking.domain.repository.PassengersTravelRepository;
import com.practice.flightbooking.domain.PassengersTravel;
import com.practice.flightbooking.persistence.crud.PassengersTravelsCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntityPk;
import com.practice.flightbooking.persistence.mapper.PassengersTravelMapper;
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
class PassengersTravelRepositoryImplTest {

    @Autowired
    private PassengersTravelRepository passengersTravelRepository;

    @Autowired
    private PassengersTravelMapper passengersTravelMapper;

    @MockBean
    private PassengersTravelsCrudRepository passengersTravelsCrudRepository;

    @Test
    @DisplayName("Should return all passengersTravelEntities with the specific passengerId and the mapper should transform to passengersTravel with only travelId or throw an error if the id is not found")
    void getPassengersTravelByIdPassenger() throws Exception {
        PassengersTravelsEntity passengersTravelsEntity1 = PassengersTravelsEntity.builder()
                .setPassengerTravelsId(PassengersTravelsEntityPk.builder()
                        .setIdTravel(33).setIdPassenger(2)
                        .create()).create();

        PassengersTravelsEntity passengersTravelsEntity2 = PassengersTravelsEntity.builder()
                .setPassengerTravelsId(PassengersTravelsEntityPk.builder()
                        .setIdTravel(44).setIdPassenger(2)
                        .create()).create();

        Optional<List<PassengersTravelsEntity>> optionalPassengersTravels = Optional.of(Arrays.asList(passengersTravelsEntity1, passengersTravelsEntity2));

        Mockito.when(passengersTravelsCrudRepository.findByPassengerTravelsIdIdPassenger(2))
                .thenReturn(optionalPassengersTravels);

        List<PassengersTravel> passengersTravels = passengersTravelRepository.getPassengersTravelByIdPassenger(2);

        Exception exception1 = assertThrows(Exception.class, () -> passengersTravelRepository.getPassengersTravelByIdPassenger(6));
        Exception exception2 = assertThrows(Exception.class, () -> Integer.parseInt("id "));

        String expectedMessage = "Passenger id not found";

        assertAll(
                () -> assertEquals(expectedMessage, exception1.getMessage()),
                () -> assertNotEquals(expectedMessage, exception2.getMessage()),
                () -> Assertions.assertThat(passengersTravels.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(33, 44), passengersTravels.stream().map(PassengersTravel::getTravelId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should transform two specific id to passengersTravelEntity and save it and then return it with the mapper to passengersTravel or throw an error if the id already exist")
    void savePassengersTravel() throws Exception {
        PassengersTravelsEntity passengersTravelsEntity = PassengersTravelsEntity.builder()
                .setPassengerTravelsId(PassengersTravelsEntityPk.builder()
                        .setIdTravel(11).setIdPassenger(1)
                        .create()).create();

        Mockito.when(passengersTravelsCrudRepository.save(ArgumentMatchers.any())).thenReturn(passengersTravelsEntity);

        PassengersTravel passengersTravelSave = passengersTravelRepository.savePassengersTravel(11, 1);

        Exception exception = assertThrows(Exception.class, () -> Integer.parseInt("id "));

        String expectedMessage = "Id already exist";

        assertAll(
                () -> assertNotEquals(expectedMessage, exception.getMessage()),
                () -> assertEquals(11, passengersTravelSave.getTravelId())
        );
    }
}