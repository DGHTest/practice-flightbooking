package com.practice.flightbooking.persistence;

import com.practice.flightbooking.domain.repository.TicketRepository;
import com.practice.flightbooking.domain.service.Ticket;
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
    public List<Ticket> getByIdPassenger(int passengerId) throws Exception {
        Optional<List<TicketEntity>> ticketsByPassenger = ticketCrudRepository.findByIdPassenger(passengerId);

        if (ticketsByPassenger.isPresent()) {
            return ticketMapper.toTickets(ticketsByPassenger.get());
        } else {
            throw new Exception("Passenger id not found");
        }
    }

    @Override
    public Ticket saveTicket(Ticket ticket) throws Exception {
        TicketEntity ticketEntity = ticketMapper.toTicketEntity(ticket);

        if (!ticketCrudRepository.existsById(ticketEntity.getIdTicket())) {
            return ticketMapper.toTicket(ticketCrudRepository.save(ticketEntity));
        } else {
            throw new Exception("Id already exist");
        }
    }
}
