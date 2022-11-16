package com.practice.flightbooking.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "departure")
public class DepartureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departure")
    private Integer idDeparture;

    @Column(name = "id_airport", nullable = false)
    private Integer idAirport;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_airport", insertable = false, updatable = false)
    private AirportEntity airportEntity;

    @OneToMany(mappedBy = "departureEntity")
    private List<TravelEntity> travelEntity;

    public Integer getIdDeparture() {
        return idDeparture;
    }

    public Integer getIdAirport() {
        return idAirport;
    }

    public Boolean getStatus() {
        return status;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public AirportEntity getAirportEntity() {
        return airportEntity;
    }

    public List<TravelEntity> getTravelEntity() {
        return travelEntity;
    }

    protected DepartureEntity() {}

    private DepartureEntity(DepartureEntity.Builder builder) {
        this.idDeparture = builder.idDeparture;
        this.idAirport = builder.idAirport;
        this.status = builder.status;
        this.departureTime = builder.departureTime;
        this.airportEntity = builder.airportEntity;
        this.travelEntity = builder.travelEntity;
    }

    public static DepartureEntity.Builder builder() {
        return new DepartureEntity.Builder();
    }

    public static class Builder {

        private Integer idDeparture;

        private Integer idAirport;

        private Boolean status = true;

        private LocalDateTime departureTime;

        private AirportEntity airportEntity;

        private List<TravelEntity> travelEntity;

        public Builder setIdDeparture(final Integer idDeparture){
            this.idDeparture = idDeparture;
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

        public Builder setDepartureTime(final LocalDateTime departureTime){
            this.departureTime = departureTime;
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

        public DepartureEntity create() {
            return new DepartureEntity(this);
        }
    }

    @Override
    public String toString() {
        return "DepartureEntity{" +
                "idDeparture=" + idDeparture +
                ", idAirport=" + idAirport +
                ", departureTime=" + departureTime +
                ", status=" + status +
                '}';
    }
}
