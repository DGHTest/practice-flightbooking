package com.practice.flightbooking.crud;

import com.practice.flightbooking.persistence.crud.TicketCrudRepository;
import com.practice.flightbooking.persistence.crud.TravelCrudRepository;
import com.practice.flightbooking.persistence.entity.TicketEntity;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql("/db/flightbooking_data.sql")
class TicketCrudRepositoryTest {

    @Autowired
    private TicketCrudRepository crudRepository;

    @Autowired
    private TestEntityManager entityManager;

    private TicketEntity ticket;

    @BeforeEach
    void setUp() {
        ticket = TicketEntity.builder()
                .setIdPassenger(1)
                .setBoardingTime(LocalDateTime.of(2023, Month.APRIL, 15, 12, 00, 00))
                .create();

        entityManager.persist(ticket);
        entityManager.flush();
    }

    @Test
    @DisplayName("Should return a ticket by his id")
    public void findById() {
        TicketEntity ticketEntity = crudRepository.findById(4).get();

        assertAll("Test with a persist entity",
                () -> Assertions.assertThat(ticket.getIdTicket()).isEqualTo(ticketEntity.getIdTicket()),
                () -> assertEquals(1, ticketEntity.getIdPassenger()),
                () -> assertEquals(LocalDateTime.of(2023, Month.APRIL, 15, 12, 00, 00), ticketEntity.getBoardingTime())
        );

        TicketEntity ticketEntity1 = crudRepository.findById(2).get();

        assertAll("Test with the data.sql",
                () -> assertEquals(2, ticketEntity1.getIdTicket()),
                () -> assertEquals(1, ticketEntity1.getIdPassenger()),
                () -> assertEquals(LocalDateTime.of(2022, Month.NOVEMBER, 19, 11, 30, 00), ticketEntity1.getBoardingTime())
        );

    }

    @Test
    @DisplayName("Should save a ticket in the database")
    public void save() {
        TicketEntity ticketEntity = TicketEntity.builder()
                .setIdPassenger(1)
                .setBoardingTime(LocalDateTime.of(2023, Month.APRIL, 15, 12, 00, 00))
                .create();

        crudRepository.save(ticketEntity);

        TicketEntity ticketSaved = crudRepository.findById(5).get();

        assertAll(
                () -> assertThat(ticketSaved.getIdTicket()).isEqualTo(ticketEntity.getIdTicket()),
                () -> assertEquals(ticketEntity.getIdPassenger(), ticketSaved.getIdPassenger()),
                () -> assertEquals(ticketEntity.getBoardingTime(), ticketSaved.getBoardingTime())
        );
    }

    @Test
    @DisplayName("Should return all specific ticket with a specific passenger id")
    public void findByIdPassenger() {
        List<TicketEntity> ticketByPassenger = crudRepository.findByIdPassenger(1).get();

        assertAll(
                () -> assertThat(ticketByPassenger.size()).isEqualTo(3),
                () -> assertEquals(Arrays.asList(1, 2, 4), ticketByPassenger.stream().map(travel -> travel.getIdTicket()).collect(Collectors.toList())),
                () -> assertEquals(Arrays.asList(1, 1, 1), ticketByPassenger.stream().map(travel -> travel.getIdPassenger()).collect(Collectors.toList()))
        );

        List<TicketEntity> ticketWithoutPassenger = crudRepository.findByIdPassenger(4).get();
        assertTrue(ticketWithoutPassenger.isEmpty());
    }
}