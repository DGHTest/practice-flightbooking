package com.practice.flightbooking.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "passenger")
@Getter
@Setter
@Builder
public class PassengerEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_passenger")
    private Integer idPassenger;

    @Column(name = "id_travel")
    private Integer idTravel;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private LocalDateTime birthDate;

    private String email;

    //TODO verificar si este apartado es un string o es otra cosa
    @Column(name = "telephone_number")
    private String telephoneNumber;

    private String country;

    private String state;

    private String city;

    @Column(name = "passport_number")
    private Integer passportNumber;

    @Column(name = "expiration_date")
    private Integer expirationDate;

    private String nationality;

    @OneToMany(mappedBy = "travelEntity", cascade = {CascadeType.ALL})
    private List<PassengersTravelEntity> passengersTravelEntities;

    @OneToMany(mappedBy = "passengerEntity")
    private List<TicketEntity> ticketEntity;
}
