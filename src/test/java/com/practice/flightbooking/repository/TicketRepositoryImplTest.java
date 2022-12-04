package com.practice.flightbooking.repository;

import com.practice.flightbooking.domain.repository.TicketRepository;
import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.persistence.crud.TicketCrudRepository;
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
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class TicketRepositoryImplTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @MockBean
    private TicketCrudRepository ticketCrudRepository;

    private Optional<List<TicketEntity>> optionalTickets;

    @BeforeEach
    void setUp() {
        TicketEntity ticketEntity1 = TicketEntity.builder()
                .setIdTicket(1)
                .setIdPassenger(4)
                .setIdTravel(32)
                .setBoardingTime(LocalDateTime.of(2024, Month.JANUARY, 12, 13, 34, 00))
                .create();

        TicketEntity ticketEntity2 = TicketEntity.builder()
                .setIdTicket(2)
                .setIdPassenger(8)
                .setIdTravel(72)
                .setBoardingTime(LocalDateTime.of(2024, Month.JULY, 12, 13, 34, 00))
                .create();

        TicketEntity ticketEntity3 = TicketEntity.builder()
                .setIdTicket(3)
                .setIdPassenger(8)
                .setIdTravel(90)
                .setBoardingTime(LocalDateTime.of(2024, Month.SEPTEMBER, 12, 13, 34, 00))
                .create();

        optionalTickets = Optional.of(Arrays.asList(ticketEntity1, ticketEntity2, ticketEntity3));
    }

    @Test
    @DisplayName("Should return one ticketEntity with his specific id and then the mapper should transform to ticket")
    void getTicketById() {
        Mockito.when(ticketCrudRepository.findById(3))
                .thenReturn(Optional.of((optionalTickets.get().get(2))));

        Ticket ticketsById = ticketRepository.getTicketById(3).get();

        assertAll(
                () -> assertEquals(3, ticketsById.getTicketId()),
                () -> assertEquals(8, ticketsById.getPassengerId()),
                () -> assertEquals(90, ticketsById.getTravelId())
        );
    }

    @Test
    @DisplayName("Should return all ticketEntities with the specific idPassenger and then the mapper should transform to tickets")
    void getByIdPassenger() {
        Mockito.when(ticketCrudRepository.findByIdPassenger(8))
                .thenReturn(Optional.of(Arrays.asList(optionalTickets.get().get(1), optionalTickets.get().get(2))));

        List<Ticket> ticketsByIdPassenger = ticketRepository.getByIdPassenger(8).get();

        assertAll(
                () -> assertThat(ticketsByIdPassenger.size()).isEqualTo(2),
                () -> assertEquals(Arrays.asList(2, 3), ticketsByIdPassenger.stream().map(Ticket::getTicketId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(8, 8), ticketsByIdPassenger.stream().map(Ticket::getPassengerId).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(72, 90), ticketsByIdPassenger.stream().map(Ticket::getTravelId).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Should save one ticketEntity and return it with the mapper to ticket")
    void saveTicket() {
        TicketEntity ticketEntity = TicketEntity.builder()
                .setIdTicket(1)
                .setIdPassenger(4)
                .setIdTravel(3)
                .setBoardingTime(LocalDateTime.now())
                .create();

        Mockito.when(ticketCrudRepository.save(ArgumentMatchers.any())).thenReturn(ticketEntity);

        Ticket saveTicket = ticketRepository.saveTicket(ticketMapper.toTicket(ticketEntity));

        assertAll(
                () -> assertEquals(ticketEntity.getIdTicket(), saveTicket.getTicketId()),
                () -> assertEquals(ticketEntity.getIdPassenger(), saveTicket.getPassengerId()),
                () -> assertEquals(ticketEntity.getIdTravel(), saveTicket.getTravelId()),
                () -> assertEquals(ticketEntity.getBoardingTime(), saveTicket.getBoardingTime())
        );
    }
}