package com.practice.flightbooking.domain.service;

import com.practice.flightbooking.persistence.entity.PassengerEntity;
import com.practice.flightbooking.persistence.entity.TicketEntity;
import com.practice.flightbooking.persistence.entity.TravelEntity;

import java.time.LocalDateTime;

public class Ticket {

    private int ticketId;

    private int passengerId;

    private int travelId;

    private LocalDateTime boardingTime;

    private Travel travel;

    public int getTicketId() {
        return ticketId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getTravelId() {
        return travelId;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public Travel getTravel() {
        return travel;
    }

    protected Ticket() {}

    private Ticket(Ticket.Builder builder) {
        this.ticketId = builder.ticketId;
        this.passengerId = builder.passengerId;
        this.travelId = builder.travelId;
        this.boardingTime = builder.boardingTime;
        this.travel = builder.travel;
    }

    public static Ticket.Builder builder() {
        return new Ticket.Builder();
    }

    public static class Builder {

        private int ticketId;

        private int passengerId;

        private int travelId;

        private LocalDateTime boardingTime;

        private Travel travel;

        public Builder setTicketId(final int ticketId){
            this.ticketId = ticketId;
            return this;
        }

        public Builder setPassengerId(final int passengerId){
            this.passengerId = passengerId;
            return this;
        }

        public Builder setTravelId(final int travelId){
            this.travelId = travelId;
            return this;
        }

        public Builder setBoardingTime(final LocalDateTime boardingTime){
            this.boardingTime = boardingTime;
            return this;
        }

        public Builder setTravel(final Travel travel){
            this.travel = travel;
            return this;
        }

        public Ticket create() {
            return new Ticket(this);
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", passengerId=" + passengerId +
                ", travelId=" + travelId +
                ", boardingTime=" + boardingTime +
                '}';
    }
}
