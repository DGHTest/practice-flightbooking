package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.persistence.entity.TicketEntity;
import com.practice.flightbooking.persistence.mapper.TicketMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class TicketMapperTest {

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    @DisplayName("Should transform the ticketEntity information to ticketService information")
    public void testForEntityToService() {

        TicketEntity entity = new TicketEntity.Builder()
                .setIdTicket(34)
                .setIdPassenger(4)
                .setIdTravel(39)
                .setBoardingTime(LocalDateTime.now())
                .create();

        Ticket ticket = ticketMapper.toTicket(entity);

        assertAll(
                () -> assertEquals(entity.getIdTicket(), ticket.getTicketId()),
                () -> assertEquals(entity.getIdPassenger(), ticket.getPassengerId()),
                () -> assertEquals(entity.getIdTravel(), ticket.getTravelId()),
                () -> assertEquals(entity.getBoardingTime(), ticket.getBoardingTime())
        );
    }

    @Test
    @DisplayName("Should transform the ticketService information to ticketEntity information")
    public void testForServiceToEntity() {
        Ticket service = Ticket.builder()
                .setTicketId(5)
                .setPassengerId(8)
                .setTravelId(13)
                .setBoardingTime(LocalDateTime.now())
                .create();

        TicketEntity ticketEntity = ticketMapper.toTicketEntity(service);

        assertAll(
                () -> assertNotNull(ticketEntity),
                () -> assertEquals(service.getTicketId(), ticketEntity.getIdTicket()),
                () -> assertEquals(service.getPassengerId(), ticketEntity.getIdPassenger()),
                () -> assertEquals(service.getTravelId(), ticketEntity.getIdTravel()),
                () -> assertEquals(service.getBoardingTime(), ticketEntity.getBoardingTime())
        );
    }

}