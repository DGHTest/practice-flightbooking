package com.practice.flightbooking.domain.repository;

import com.practice.flightbooking.domain.service.Ticket;
import com.practice.flightbooking.domain.service.Travel;

import java.util.List;

public interface TicketRepository {

    List<Ticket> getByIdPassenger(int passengerId) throws Exception;

    Ticket saveTicket(Ticket ticket) throws Exception;
}
