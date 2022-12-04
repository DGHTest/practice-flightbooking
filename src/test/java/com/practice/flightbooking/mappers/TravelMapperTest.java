package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.Travel;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import com.practice.flightbooking.persistence.mapper.TravelMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("dev")
class TravelMapperTest {

    @Autowired
    private TravelMapper travelMapper;

    @Test
    @DisplayName("Should transform the travelEntity information to travelService information")
    public void testForEntityToService() {
        TravelEntity entity = TravelEntity.builder()
                .setIdTravel(53)
                .setPrice(BigDecimal.valueOf(543.54))
                .setIdDeparture(5)
                .setIdArrivalFlight(3)
                .create();

        Travel travel = travelMapper.toTravel(entity);

        assertAll(
                () -> assertEquals(entity.getIdTravel(), travel.getTravelId()),
                () -> assertEquals(entity.getPrice(), travel.getPrice()),
                () -> assertEquals(entity.getIdDeparture(), travel.getDepartureId()),
                () -> assertEquals(entity.getIdArrivalFlight(), travel.getArrivalFlightId())
        );

    }

    @Test
    @DisplayName("Should transform the travelService information to travelEntity information")
    public void testForServiceToEntity() {
        Travel service = Travel.builder()
                .setTravelId(89)
                .setPrice(BigDecimal.valueOf(646.65))
                .setDepartureId(76)
                .setArrivalFlightId(5)
                .create();

        TravelEntity travelEntity = travelMapper.toTravelEntity(service);

        assertAll(
                () -> assertEquals(service.getTravelId(), travelEntity.getIdTravel()),
                () -> assertEquals(service.getPrice(), travelEntity.getPrice()),
                () -> assertEquals(service.getDepartureId(), travelEntity.getIdDeparture()),
                () -> assertEquals(service.getArrivalFlightId(), travelEntity.getIdArrivalFlight())
        );
    }
}