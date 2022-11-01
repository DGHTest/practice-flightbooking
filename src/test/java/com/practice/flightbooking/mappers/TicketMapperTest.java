package com.practice.flightbooking.mappers;

import com.practice.flightbooking.domain.service.Ticket;
import com.practice.flightbooking.persistence.entity.TicketEntity;
import com.practice.flightbooking.persistence.mapper.TicketMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TicketMapperTest {

    private TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Test
    @DisplayName("Should transform the ticketEntity information to ticketService information")
    public void testForEntityToService() {

        TicketEntity entity = new TicketEntity.Builder()
                .setIdPassenger(4)
                .setBoardingTime(LocalDateTime.now())
                .create();

        Ticket ticket = ticketMapper.toTicket(entity);

        //assertNull(entity.getIdTicket());
        assertNotNull(ticket.getTicketId());
        assertEquals(entity.getIdPassenger(), ticket.getPassengerId());
        assertEquals(entity.getBoardingTime(), ticket.getBoardingTime());

    }

    @Test
    @DisplayName("Should transform the ticketService information to ticketEntity information")
    public void testForServiceToEntity() {
        Ticket service = new Ticket();
        service.setTicketId(5);
        service.setPassengerId(8);

        TicketEntity ticketEntity = ticketMapper.toTicketEntity(service);

        assertNotNull(ticketEntity);
        assertEquals(service.getPassengerId(), ticketEntity.getIdPassenger());
        assertEquals(service.getBoardingTime(), ticketEntity.getBoardingTime());
    }

}