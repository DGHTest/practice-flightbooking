package com.practice.flightbooking.service;

import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.domain.repository.TicketRepository;
import com.practice.flightbooking.domain.service.TicketService;
import com.practice.flightbooking.persistence.entity.TicketEntity;
import com.practice.flightbooking.persistence.mapper.TicketMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @MockBean
    private TicketRepository ticketRepository;

    private List<Ticket> tickets;

    @BeforeEach
    void setUp() {
        Ticket ticket1 = Ticket.builder()
                .setTicketId(1)
                .setPassengerId(4)
                .setTravelId(32)
                .setBoardingTime(LocalDateTime.of(2024, Month.JANUARY, 12, 13, 34, 00))
                .create();

        Ticket ticket2 = Ticket.builder()
                .setTicketId(2)
                .setPassengerId(8)
                .setTravelId(72)
                .setBoardingTime(LocalDateTime.of(2024, Month.JULY, 12, 13, 34, 00))
                .create();

        Ticket ticket3 = Ticket.builder()
                .setTicketId(3)
                .setPassengerId(8)
                .setTravelId(90)
                .setBoardingTime(LocalDateTime.of(2024, Month.SEPTEMBER, 12, 13, 34, 00))
                .create();

        tickets = Arrays.asList(ticket1, ticket2, ticket3);
    }

    @Test
    @DisplayName("Should return one ticket with his specific id using the repository")
    void getTicketById() throws Exception {
        Mockito.when(ticketRepository.getTicketById(3))
                .thenReturn(tickets.get(2));

        Ticket ticketsById = ticketService.getTicketById(3);

        assertAll(
                () -> assertEquals(3, ticketsById.getTicketId()),
                () -> assertEquals(8, ticketsById.getPassengerId()),
                () -> assertEquals(90, ticketsById.getTravelId())
        );
    }

    @Test
    @DisplayName("Should return all tickets with the specific idPassenger using the repository")
    void getByIdPassenger() throws Exception {
        Mockito.when(ticketRepository.getByIdPassenger(8))
                .thenReturn(Arrays.asList(tickets.get(1), tickets.get(2)));

        List<Ticket> ticketsByIdPassenger = ticketService.getByIdPassenger(8);

        assertAll(
                () -> assertThat(ticketsByIdPassenger.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(2, 3), ticketsByIdPassenger.stream().map(Ticket::getTicketId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(8, 8), ticketsByIdPassenger.stream().map(Ticket::getPassengerId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(72, 90), ticketsByIdPassenger.stream().map(Ticket::getTravelId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should save one ticket and return it using the repository")
    void saveTicket() throws Exception {
        Ticket ticket = Ticket.builder()
                .setTicketId(1)
                .setPassengerId(4)
                .setTravelId(3)
                .setBoardingTime(LocalDateTime.now())
                .create();

        Mockito.when(ticketRepository.saveTicket(ArgumentMatchers.any())).thenReturn(ticket);

        Ticket saveTicket = ticketRepository.saveTicket(ticket);

        assertAll(
                () -> assertEquals(ticket.getTicketId(), saveTicket.getTicketId()),
                () -> assertEquals(ticket.getPassengerId(), saveTicket.getPassengerId()),
                () -> assertEquals(ticket.getTravelId(), saveTicket.getTravelId()),
                () -> assertEquals(ticket.getBoardingTime(), saveTicket.getBoardingTime())
        );
    }
}