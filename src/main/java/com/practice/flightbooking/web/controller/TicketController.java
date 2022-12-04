package com.practice.flightbooking.web.controller;

import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.domain.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") int ticketId) {
        return ticketService.getTicketById(ticketId)
                .map(ticket -> new ResponseEntity<>(ticket, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/passenger/{id}")
    public ResponseEntity<List<Ticket>> getByIdPassenger(@PathVariable("id") int passengerId) {
        return ticketService.getByIdPassenger(passengerId)
                .map(tickets -> new ResponseEntity<>(tickets, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket) throws Exception {
        return new ResponseEntity<>(ticketService.saveTicket(ticket), HttpStatus.CREATED);
    }
}
