package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.TicketRepository;
import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.persistence.crud.TicketCrudRepository;
import com.practice.flightbooking.persistence.entity.TicketEntity;
import com.practice.flightbooking.persistence.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    private TicketCrudRepository ticketCrudRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public Optional<Ticket> getTicketById(int ticketId) {
        return ticketCrudRepository.findById(ticketId)
                .map(ticket -> ticketMapper.toTicket(ticket));
    }

    @Override
    public Optional<List<Ticket>> getByIdPassenger(int passengerId) {
        return ticketCrudRepository.findByIdPassenger(passengerId)
                .map(tickets -> ticketMapper.toTickets(tickets));
    }

    @Override
    public Ticket saveTicket(Ticket ticket){
        TicketEntity ticketEntity = ticketMapper.toTicketEntity(ticket);
        return ticketMapper.toTicket(ticketCrudRepository.save(ticketEntity));
    }
}
