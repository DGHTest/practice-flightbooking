package com.practice.flightbooking.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Integer idTicket;

    @Column(name = "id_passenger", nullable = false)
    private Integer idPassenger;

    @Column(name = "id_travel", nullable = false)
    private Integer idTravel;

    @Column(name = "boarding_time", nullable = false)
    private LocalDateTime boardingTime;

    @ManyToOne
    @JoinColumn(name = "id_passenger", insertable = false, updatable = false)
    private PassengerEntity passengerEntity;

    @ManyToOne
    @JoinColumn(name = "id_travel", insertable = false, updatable = false)
    private TravelEntity travelEntity;

    public Integer getIdTicket() {
        return idTicket;
    }

    public Integer getIdPassenger() {
        return idPassenger;
    }

    public Integer getIdTravel() {
        return idTravel;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public PassengerEntity getPassengerEntity() {
        return passengerEntity;
    }

    public TravelEntity getTravelEntity() {
        return travelEntity;
    }

    protected TicketEntity() {}

    private TicketEntity(TicketEntity.Builder builder) {
        this.idTicket = builder.idTicket;
        this.idPassenger = builder.idPassenger;
        this.idTravel = builder.idTravel;
        this.boardingTime = builder.boardingTime;
        this.passengerEntity = builder.passengerEntity;
        this.travelEntity = builder.travelEntity;
    }

    public static TicketEntity.Builder builder() {
        return new TicketEntity.Builder();
    }

    public static class Builder {

        private Integer idTicket;

        private Integer idPassenger;

        private Integer idTravel;

        private LocalDateTime boardingTime;

        private PassengerEntity passengerEntity;

        private TravelEntity travelEntity;

        public Builder setIdTicket(final Integer idTicket){
            this.idTicket = idTicket;
            return this;
        }

        public Builder setIdPassenger(final Integer idPassenger){
            this.idPassenger = idPassenger;
            return this;
        }

        public Builder setIdTravel(final Integer idTravel){
            this.idTravel = idTravel;
            return this;
        }

        public Builder setBoardingTime(final LocalDateTime boardingTime){
            this.boardingTime = boardingTime;
            return this;
        }

        public Builder setPassengerEntity(final PassengerEntity passengerEntity){
            this.passengerEntity = passengerEntity;
            return this;
        }

        public Builder setTravelEntity(final TravelEntity travelEntity){
            this.travelEntity = travelEntity;
            return this;
        }

        public TicketEntity create() {
            return new TicketEntity(this);
        }
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "idTicket=" + idTicket +
                ", idPassenger=" + idPassenger +
                ", idTravel=" + idTravel +
                ", boardingTime=" + boardingTime +
                '}';
    }
}
