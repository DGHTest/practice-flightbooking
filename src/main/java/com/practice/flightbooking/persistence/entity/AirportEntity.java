package com.practice.flightbooking.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "airport")
@Data
public class AirportEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_airport")
    private Integer idAirport;

    private String name;

    private String iata;

    private String country;

    private String city;

    @Column(name = "time_zone")
    private String timeZone;

    @OneToMany(mappedBy = "airport")
    private List<ArrivalFlightEntity> arrivalFlightEntities;

    @OneToMany(mappedBy = "airport")
    private List<DepartureEntity> departureFlightEntities;
}
