package com.practice.flightbooking.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_ticket")
    private Integer idTicket;

    @Column(name = "id_passenger")
    private Integer idPassenger;

    @Column(name = "boarding_time")
    private LocalDateTime boardingTime;

    @ManyToOne
    @JoinColumn(name = "id_passenger", insertable = false, updatable = false)
    private PassengerEntity passengerEntity;
}
