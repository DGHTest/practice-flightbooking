package com.practice.flightbooking.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@Data
public class TicketEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_ticket")
    private Integer idTicket;

    @Column(name = "boarding_time")
    private LocalDateTime boardingTime;

    @ManyToOne
    @JoinColumn(name = "id_passenger", insertable = false, updatable = false)
    private PassengerEntity passengerEntity;
}
