package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.service.PassengersTravel;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntityPk;
import com.practice.flightbooking.persistence.mapper.PassengersTravelMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class PassengersTravelMapperTest {

    private PassengersTravelMapper mapper = Mappers.getMapper(PassengersTravelMapper.class);

    @Test
    @DisplayName("Should transform the passengersTravelEntity information to passengersTravelService information")
    public void testForEntityToService() {

        PassengersTravelsEntityPk entityPK = PassengersTravelsEntityPk.builder().setIdTravel(7).create();

        PassengersTravelsEntity entity = PassengersTravelsEntity.builder().setPassengerTravelsId(entityPK).create();

        PassengersTravel passengersTravel = mapper.toPassengersTravel(entity);

        assertAll(
                () -> assertNotNull(passengersTravel),
                () -> assertEquals(entity.getPassengerTravelsId().getIdTravel(), passengersTravel.getTravelId())
        );

    }

    @Test
    @DisplayName("Should transform the passengersTravelservice information to passengersTravelentity information")
    public void testForServiceToEntity() {

        PassengersTravel service = PassengersTravel.builder()
                .setTravelId(86)
                .create();

        PassengersTravelsEntity passengersTravelsEntity = mapper.toPassengersTravelEntity(service);


        assertNotNull(passengersTravelsEntity);
        assertEquals(service.getTravelId(), passengersTravelsEntity.getPassengerTravelsId().getIdTravel());
    }

}