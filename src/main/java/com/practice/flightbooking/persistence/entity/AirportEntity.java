package com.practice.flightbooking.persistence.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "airport")
public class AirportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_airport")
    private Integer idAirport;

    @Column(length=200, nullable=false)
    private String name;

    @Column(length=50, nullable=false)
    private String country;

    @Column(length=100, nullable=false)
    private String state;

    @Column(length=100, nullable=false)
    private String city;

    @Column(length=3, nullable=false, unique = true)
    private String iata;

    @Column(length=4, nullable=false, unique = true)
    private String icao;

    @OneToMany(mappedBy = "airportEntity")
    private List<ArrivalFlightEntity> arrivalFlightEntities;

    @OneToMany(mappedBy = "airportEntity")
    private List<DepartureEntity> departureEntities;

    public Integer getIdAirport() {
        return idAirport;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getIata() {
        return iata;
    }

    public String getIcao() {
        return icao;
    }

    public List<ArrivalFlightEntity> getArrivalFlightEntities() {
        return arrivalFlightEntities;
    }

    public List<DepartureEntity> getDepartureEntities() {
        return departureEntities;
    }

    protected AirportEntity() {}

    private AirportEntity(AirportEntity.Builder builder) {
        this.idAirport = builder.idAirport;
        this.name = builder.name;
        this.country = builder.country;
        this.state = builder.state;
        this.city = builder.city;
        this.iata = builder.iata;
        this.icao = builder.icao;
        this.arrivalFlightEntities = builder.arrivalFlightEntities;
        this.departureEntities = builder.departureEntities;
    }
    public static AirportEntity.Builder builder() {
        return new AirportEntity.Builder();
    }

    public static class Builder {

        private Integer idAirport;

        private String name;

        private String country;

        private String state;

        private String city;

        private String iata;

        private String icao;

        private List<ArrivalFlightEntity> arrivalFlightEntities;

        private List<DepartureEntity> departureEntities;

        public Builder setIdAirport(final Integer idAirport){
            this.idAirport = idAirport;
            return this;
        }

        public Builder setName(final String name){
            this.name = name;
            return this;
        }

        public Builder setCountry(final String country){
            this.country = country;
            return this;
        }
        public Builder setState(final String state){
            this.state = state;
            return this;
        }

        public Builder setCity(final String city){
            this.city = city;
            return this;
        }

        public Builder setIata(final String iata){
            this.iata = iata;
            return this;
        }

        public Builder setIcao(final String icao){
            this.icao = icao;
            return this;
        }

        public Builder setArrivalFlightEntities(final List<ArrivalFlightEntity> arrivalFlightEntities){
            this.arrivalFlightEntities = arrivalFlightEntities;
            return this;
        }

        public Builder setDepartureEntities(final List<DepartureEntity> departureEntities){
            this.departureEntities = departureEntities;
            return this;
        }

        public AirportEntity create() {
            return new AirportEntity(this);
        }
    }

    @Override
    public String toString() {
        return "AirportEntity{" +
                "idAirport=" + idAirport +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", iata='" + iata + '\'' +
                ", icao='" + icao + '\'' +
                '}';
    }
}
