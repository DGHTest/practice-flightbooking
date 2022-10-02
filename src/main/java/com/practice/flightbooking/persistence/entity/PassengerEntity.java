package com.practice.flightbooking.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "passenger")
@Data
public class PassengerEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_passenger")
    private Integer idPassenger;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private LocalDateTime birthDate;

    private String email;

    //TODO verificar si este apartado es un string o es otra cosa
    @Column(name = "telephone_number")
    private Integer telephoneNumber;

    private String country;

    private String state;

    private String city;

    @Column(name = "passport_number")
    private Integer passportNumber;

    @Column(name = "expiration_date")
    private Integer expirationDate;

    private String nationality;

    @ManyToMany
    private List<TravelEntity> travelEntity;

    @OneToMany
    private TicketEntity ticketEntity;
}
