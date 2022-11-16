package com.practice.flightbooking.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "travel")
public class TravelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_travel")
    private Integer idTravel;


    @Column(name = "id_arrival_flight", nullable = false)
    private Integer idArrivalFlight;

    @Column(name = "id_departure", nullable = false)
    private Integer idDeparture;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer = 8, fraction = 2)
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_departure", insertable = false, updatable = false)
    private DepartureEntity departureEntity;

    @ManyToOne
    @JoinColumn(name = "id_arrival_flight", insertable = false, updatable = false)
    private ArrivalFlightEntity arrivalFlightEntity;

    @OneToMany(mappedBy = "travelEntity")
    private List<PassengersTravelsEntity> passengersEntities;

    @OneToMany(mappedBy = "idTravel")
    private List<TicketEntity> ticketEntities;

    public Integer getIdTravel() {
        return idTravel;
    }

    public Integer getIdArrivalFlight() {
        return idArrivalFlight;
    }

    public Integer getIdDeparture() {
        return idDeparture;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getStatus() {
        return status;
    }

    public DepartureEntity getDepartureEntity() {
        return departureEntity;
    }

    public ArrivalFlightEntity getArrivalFlightEntity() {
        return arrivalFlightEntity;
    }

    public List<PassengersTravelsEntity> getPassengersEntities() {
        return passengersEntities;
    }

    public List<TicketEntity> getTicketEntities() {
        return ticketEntities;
    }

    protected TravelEntity() {}

    private TravelEntity(TravelEntity.Builder builder) {
        this.idTravel = builder.idTravel;
        this.idArrivalFlight = builder.idArrivalFlight;
        this.idDeparture = builder.idDeparture;
        this.price = builder.price;
        this.status = builder.status;
        this.departureEntity = builder.departureEntity;
        this.arrivalFlightEntity = builder.arrivalFlightEntity;
        this.passengersEntities = builder.passengersEntities;
        this.ticketEntities = builder.ticketEntities;
    }

    public static TravelEntity.Builder builder() {
        return new TravelEntity.Builder();
    }

    public static class Builder {

        private Integer idTravel;

        private Integer idArrivalFlight;

        private Integer idDeparture;

        private BigDecimal price;

        private Boolean status = true;

        private DepartureEntity departureEntity;

        private ArrivalFlightEntity arrivalFlightEntity;

        private List<PassengersTravelsEntity> passengersEntities;

        private List<TicketEntity> ticketEntities;

        public Builder setIdTravel(final Integer idTravel){
            this.idTravel = idTravel;
            return this;
        }

        public Builder setIdArrivalFlight(final Integer idArrivalFlight){
            this.idArrivalFlight = idArrivalFlight;
            return this;
        }

        public Builder setIdDeparture(final Integer idDeparture){
            this.idDeparture = idDeparture;
            return this;
        }

        public Builder setPrice(final BigDecimal price){
            this.price = price;
            return this;
        }

        public Builder setStatus(final Boolean status){
            this.status = status;
            return this;
        }

        public Builder setDepartureEntity(final DepartureEntity departureEntity){
            this.departureEntity = departureEntity;
            return this;
        }

        public Builder setArrivalFlightEntity(final ArrivalFlightEntity arrivalFlightEntity){
            this.arrivalFlightEntity = arrivalFlightEntity;
            return this;
        }

        public Builder setPassengersEntities(final List<PassengersTravelsEntity> passengersEntities){
            this.passengersEntities = passengersEntities;
            return this;
        }

        public Builder setTicketEntities(final List<TicketEntity> ticketEntities){
            this.ticketEntities = ticketEntities;
            return this;
        }

        public TravelEntity create() {
            return new TravelEntity(this);
        }
    }

    @Override
    public String toString() {
        return "TravelEntity{" +
                "idTravel=" + idTravel +
                ", idArrivalFlight=" + idArrivalFlight +
                ", idDeparture=" + idDeparture +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
