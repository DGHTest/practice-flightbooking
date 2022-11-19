package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.domain.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket getTicketById(int ticket) throws Exception {
        return ticketRepository.getTicketById(ticket);
    }

    public List<Ticket> getByIdPassenger(int passengerId) throws Exception {
        return ticketRepository.getByIdPassenger(passengerId);
    }

    public Ticket saveTicket(Ticket ticket) throws Exception {
        return ticketRepository.saveTicket(ticket);
    }

}
