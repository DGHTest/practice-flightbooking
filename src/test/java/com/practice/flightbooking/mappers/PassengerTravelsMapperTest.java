package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.service.PassengersTravel;
import com.practice.flightbooking.domain.service.Travel;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntityPk;
import com.practice.flightbooking.persistence.mapper.PassengerTravelsMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class PassengerTravelsMapperTest {

    private PassengerTravelsMapper mapper = Mappers.getMapper(PassengerTravelsMapper.class);

    @Test
    @DisplayName("Should transform the passengersTravelEntity information to passengersTravelService information")
    public void testForEntityToService() {

        PassengersTravelsEntityPk entityPK = PassengersTravelsEntityPk.builder().setIdTravel(7).create();

        PassengersTravelsEntity entity = PassengersTravelsEntity.builder().setPassengerTravelsId(entityPK).create();

        PassengersTravel passengersTravel = mapper.toPassengersTravel(entity);

        Assertions.assertNotNull(passengersTravel);
        Assertions.assertEquals(entity.getPassengerTravelsId().getIdTravel(), passengersTravel.getTravelsPassengerId());

    }

    @Test
    @DisplayName("Should transform the passengersTravelservice information to passengersTravelentity information")
    public void testForServiceToEntity() {
        Travel travel = new Travel();
        travel.setTravelId(86);

        PassengersTravel service = new PassengersTravel();
        service.setTravelsPassengerId(86);

        PassengersTravelsEntity passengersTravelsEntity = mapper.toPassengersTravelEntity(service);


        Assertions.assertNotNull(passengersTravelsEntity);
        Assertions.assertEquals(service.getTravelsPassengerId(), passengersTravelsEntity.getPassengerTravelsId().getIdTravel());
    }

}