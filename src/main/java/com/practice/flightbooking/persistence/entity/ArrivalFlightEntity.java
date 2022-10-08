package com.practice.flightbooking.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "arrival_flight")
@SecondaryTable(name = "airport", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_airport"))
@Getter
@Setter
@Builder
public class ArrivalFlightEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_arrival_flight")
    private Integer idArrivalFlight;

    @Column(name = "id_airport")
    private Integer idAirport;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    private Boolean status;

    @Embedded
    private AirportEntity airportEntity;

    @OneToMany(mappedBy = "arrivalFlight", cascade = {CascadeType.ALL})
    private List<TravelEntity> travelEntity;


}
