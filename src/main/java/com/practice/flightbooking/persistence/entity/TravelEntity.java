package com.practice.flightbooking.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "travel")
@Data
public class TravelEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_travel")
    private Integer idTravel;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_departure_flight", insertable = false, updatable = false)
    private DepartureEntity departureEntity;

    @ManyToOne
    @JoinColumn(name = "id_arrival_flight", insertable = false, updatable = false)
    private ArrivalFlightEntity arrivalFlightEntity;

    @ManyToMany
    private List<PassengerEntity> passengerEntities;
}
