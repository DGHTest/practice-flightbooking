package com.practice.flightbooking.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passengers_travel")
@Getter
@Setter
@Builder
public class PassengersTravelEntity {

    @EmbeddedId
    private PassengersTravelEntityPK id;

    @ManyToOne
    @MapsId("idPassenger")
    @JoinColumn(name = "id_passenger", insertable = false, updatable = false)
    private PassengerEntity passengerEntities;

    @ManyToOne
    @JoinColumn(name = "id_travel", insertable = false, updatable = false)
    private TravelEntity travelEntity;
}
