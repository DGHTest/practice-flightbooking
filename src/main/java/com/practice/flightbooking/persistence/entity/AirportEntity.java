package com.practice.flightbooking.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Embeddable
@Getter
@Setter
@Builder
public class AirportEntity {

    private String country;

    private String state;

    private String city;

    private String iata;
}
