package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.Ticket;

import java.util.List;

public interface TicketRepository {

    Ticket getTicketById(int ticketId) throws Exception;
    List<Ticket> getByIdPassenger(int passengerId) throws Exception;

    Ticket saveTicket(Ticket ticket) throws Exception;
}
