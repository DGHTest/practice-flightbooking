package com.practice.flightbooking.controller.precondition;

import com.practice.flightbooking.persistence.crud.PassengersTravelsCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntityPk;
import com.practice.flightbooking.web.controller.precondition.CapacityTravel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class CapacityTravelTest {

    @Autowired
    private CapacityTravel capacityTravel;

    @MockBean
    private PassengersTravelsCrudRepository passengersTravelsCrudRepository;

    @Test
    void capacity_in_CapacityTravel_true() {
        Mockito.when(passengersTravelsCrudRepository.findByPassengerTravelsIdIdTravel(654))
                .thenReturn(Optional.empty());

        assertTrue(capacityTravel.capacity(654));
    }

    @Test
    void capacity_in_CapacityTravel_false_with_sizeMaxOf2() {
        PassengersTravelsEntity passengersTravelsEntity1 = PassengersTravelsEntity.builder()
                .setPassengerTravelsId(PassengersTravelsEntityPk.builder().setIdPassenger(321).setIdTravel(654).create()).create();

        PassengersTravelsEntity passengersTravelsEntity2 = PassengersTravelsEntity.builder()
                .setPassengerTravelsId(PassengersTravelsEntityPk.builder().setIdPassenger(453).setIdTravel(654).create()).create();

        Mockito.when(passengersTravelsCrudRepository.findByPassengerTravelsIdIdTravel(654))
                .thenReturn(Optional.of(Arrays.asList(passengersTravelsEntity1, passengersTravelsEntity2)));

        assertFalse(capacityTravel.capacity(654));
    }
}