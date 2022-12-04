package com.practice.flightbooking.domain;

import java.time.LocalDateTime;
import java.util.List;


public class ArrivalFlight {

    private int arrivalFlightId;

    private int airportId;

    private LocalDateTime arrivalTime;

    private Airport airport;

    public int getArrivalFlightId() {
        return arrivalFlightId;
    }

    public int getAirportId() {
        return airportId;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Airport getAirport() {
        return airport;
    }

    protected ArrivalFlight() {}

    private ArrivalFlight(ArrivalFlight.Builder builder) {
        this.arrivalFlightId = builder.arrivalFlightId;
        this.airportId = builder.airportId;
        this.arrivalTime = builder.arrivalTime;
        this.airport = builder.airport;
    }

    public static ArrivalFlight.Builder builder() {
        return new ArrivalFlight.Builder();
    }

    public static class Builder {

        private int arrivalFlightId;

        private int airportId;

        private LocalDateTime arrivalTime;

        private Airport airport;

        public Builder setArrivalFlightId(final int arrivalFlightId){
            this.arrivalFlightId = arrivalFlightId;
            return this;
        }

        public Builder setAirportId(final int airportId){
            this.airportId = airportId;
            return this;
        }

        public Builder setArrivalTime(final LocalDateTime arrivalTime){
            this.arrivalTime = arrivalTime;
            return this;
        }

        public Builder setAirport(final Airport airport){
            this.airport = airport;
            return this;
        }
        public ArrivalFlight create() {
            return new ArrivalFlight(this);
        }
    }

    @Override
    public String toString() {
        return "ArrivalFlight{" +
                "arrivalFlightId=" + arrivalFlightId +
                ", airportId=" + airportId +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
