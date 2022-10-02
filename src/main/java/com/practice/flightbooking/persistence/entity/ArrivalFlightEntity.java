package com.practice.flightbooking.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "arrival_flight")
@Data
public class ArrivalFlightEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_arrival_flight")
    private Integer idArrivalFlight;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "id_airport", insertable = false, updatable = false)
    private AirportEntity airportEntity;

    @OneToMany(mappedBy = "arrivalFlight", cascade = {CascadeType.ALL})
    private List<TravelEntity> travelEntity;
}
