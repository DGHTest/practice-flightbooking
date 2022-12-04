package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.domain.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Optional<Ticket> getTicketById(int ticket) {
        return ticketRepository.getTicketById(ticket);
    }

    public Optional<List<Ticket>> getByIdPassenger(int passengerId) {
        return ticketRepository.getByIdPassenger(passengerId);
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.saveTicket(ticket);
    }
}
