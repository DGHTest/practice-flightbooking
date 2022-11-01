package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.service.Travel;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import com.practice.flightbooking.persistence.mapper.TravelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

class TravelMapperTest {

    private TravelMapper travelMapper = Mappers.getMapper(TravelMapper.class);

    @Test
    @DisplayName("Should transform the travelEntity information to travelService information")
    public void testForEntityToService() {
        TravelEntity entity = new TravelEntity.Builder()
                .setPrice(BigDecimal.valueOf(543.54))
                .setIdDeparture(5)
                .setIdArrivalFlight(3)
                .create();

        Travel travel = travelMapper.toTravel(entity);

        Assertions.assertAll(
                () -> Assertions.assertEquals(entity.getPrice(), travel.getPrice()),
                () -> Assertions.assertEquals(entity.getIdDeparture(), travel.getDepartureId()),
                () -> Assertions.assertEquals(entity.getIdArrivalFlight(), travel.getArrivalFlightId())
        );

    }

    @Test
    @DisplayName("Should transform the travelService information to travelEntity information")
    public void testForServiceToEntity() {
        Travel service = new Travel();
        service.setTravelId(89);
        service.setPrice(BigDecimal.valueOf(646.65));
        service.setDepartureId(76);
        service.setArrivalFlightId(5);

        TravelEntity travelEntity = travelMapper.toTravelEntity(service);

        Assertions.assertEquals(service.getTravelId(), travelEntity.getIdTravel());
        Assertions.assertEquals(service.getPrice(), travelEntity.getPrice());
    }
}