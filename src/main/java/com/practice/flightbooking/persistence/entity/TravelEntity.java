package com.practice.flightbooking.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "travel")
@Getter
@Setter
@Builder
public class TravelEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_travel")
    private Integer idTravel;

    @Column(name = "id_arrival_flight")
    private Integer idArrivalFlight;

    @Column(name = "id_departure")
    private Integer idDeparture;

    @Column(name = "id_passenger")
    private Integer idPassenger;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_departure", insertable = false, updatable = false)
    private DepartureEntity departureEntity;

    @ManyToOne
    @JoinColumn(name = "id_arrival_flight", insertable = false, updatable = false)
    private ArrivalFlightEntity arrivalFlightEntity;
}
