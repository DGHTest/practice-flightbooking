package com.practice.flightbooking.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
public class PassengersTravelEntityPK implements Serializable {

    @Column(name = "id_passenger")
    private Integer idPassenger;

    @Column(name = "id_travel")
    private Integer idTravel;
}
