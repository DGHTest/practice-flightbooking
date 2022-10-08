package com.practice.flightbooking.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "departure_flight")
@SecondaryTable(name = "airport", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_airport"))
@Getter
@Setter
@Builder
public class DepartureEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_departure")
    private Integer idDeparture;

    @Column(name = "id_airport")
    private Integer idAirport;

    private Boolean status;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Embedded
    private AirportEntity airportEntity;

    @OneToMany(mappedBy = "departureFlight", cascade = {CascadeType.ALL})
    private List<TravelEntity> travelEntity;
}
