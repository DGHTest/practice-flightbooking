package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {

    Optional<Ticket> getTicketById(int ticketId);
    Optional<List<Ticket>> getByIdPassenger(int passengerId);

    Ticket saveTicket(Ticket ticket);
}
