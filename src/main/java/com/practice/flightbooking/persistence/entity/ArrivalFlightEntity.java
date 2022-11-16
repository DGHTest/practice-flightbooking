package com.practice.flightbooking.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "arrival_flight")
public class ArrivalFlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arrival_flight")
    private Integer idArrivalFlight;

    @Column(name = "id_airport", nullable = false)
    private Integer idAirport;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_airport", insertable = false, updatable = false)
    private AirportEntity airportEntity;

    @OneToMany(mappedBy = "arrivalFlightEntity")
    private List<TravelEntity> travelEntity;

    public Integer getIdArrivalFlight() {
        return idArrivalFlight;
    }

    public Integer getIdAirport() {
        return idAirport;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public AirportEntity getAirportEntity() {
        return airportEntity;
    }

    public List<TravelEntity> getTravelEntity() {
        return travelEntity;
    }

    protected ArrivalFlightEntity() {}

    private ArrivalFlightEntity(ArrivalFlightEntity.Builder builder) {
        this.idArrivalFlight = builder.idArrivalFlight;
        this.idAirport = builder.idAirport;
        this.status = builder.status;
        this.arrivalTime = builder.arrivalTime;
        this.airportEntity = builder.airportEntity;
        this.travelEntity = builder.travelEntity;
    }

    public static ArrivalFlightEntity.Builder builder() {
        return new ArrivalFlightEntity.Builder();
    }

    public static class Builder {

        private Integer idArrivalFlight;

        private Integer idAirport;

        private Boolean status = true;

        private LocalDateTime arrivalTime;

        private AirportEntity airportEntity;

        private List<TravelEntity> travelEntity;

        public Builder setIdArrivalFlight(final Integer idArrivalFlight){
            this.idArrivalFlight = idArrivalFlight;
            return this;
        }

        public Builder setIdAirport(final Integer idAirport){
            this.idAirport = idAirport;
            return this;
        }

        public Builder setStatus(final Boolean status){
            this.status = status;
            return this;
        }

        public Builder setArrivalTime(final LocalDateTime arrivalTime){
            this.arrivalTime = arrivalTime;
            return this;
        }

        public Builder setAirportEntity(final AirportEntity airportEntity){
            this.airportEntity = airportEntity;
            return this;
        }

        public Builder setTravelEntity(final List<TravelEntity> travelEntity){
            this.travelEntity = travelEntity;
            return this;
        }

        public ArrivalFlightEntity create() {
            return new ArrivalFlightEntity(this);
        }
    }

    @Override
    public String toString() {
        return "ArrivalFlightEntity{" +
                "idArrivalFlight=" + idArrivalFlight +
                ", idAirport=" + idAirport +
                ", arrivalTime=" + arrivalTime +
                ", status=" + status +
                '}';
    }
}
