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

    @Column(name = "boarding_time", nullable = false)
    private LocalDateTime boardingTime;

    @ManyToOne
    @JoinColumn(name = "id_passenger", insertable = false, updatable = false)
    private PassengerEntity passengerEntity;

    public Integer getIdTicket() {
        return idTicket;
    }

    public Integer getIdPassenger() {
        return idPassenger;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public PassengerEntity getPassengerEntity() {
        return passengerEntity;
    }

    protected TicketEntity() {}

    private TicketEntity(TicketEntity.Builder builder) {
        this.idTicket = builder.idTicket;
        this.idPassenger = builder.idPassenger;
        this.boardingTime = builder.boardingTime;
        this.passengerEntity = builder.passengerEntity;
    }

    public static TicketEntity.Builder builder() {
        return new TicketEntity.Builder();
    }

    public static class Builder {

        private Integer idTicket;

        private Integer idPassenger;

        private LocalDateTime boardingTime;

        private PassengerEntity passengerEntity;

        public Builder setIdTicket(final Integer idTicket){
            this.idTicket = idTicket;
            return this;
        }

        public Builder setIdPassenger(final Integer idPassenger){
            this.idPassenger = idPassenger;
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

        public TicketEntity create() {
            validate();
            return new TicketEntity(this);
        }

        private void validate() {

        }
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "idTicket=" + idTicket +
                ", idPassenger=" + idPassenger +
                ", boardingTime=" + boardingTime +
                '}';
    }
}
