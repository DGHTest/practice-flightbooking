package com.practice.flightbooking.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "departure_flight")
@Data
public class DepartureEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_departure_flight")
    private Integer idDepartureFlight;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @ManyToOne
    @JoinColumn(name = "id_airport", insertable = false, updatable = false)
    private AirportEntity airportEntity;

    @OneToMany(mappedBy = "departureFlight")
    private TravelEntity travelEntity;
}
