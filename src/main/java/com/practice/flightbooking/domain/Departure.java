package com.practice.flightbooking.domain;

import java.time.LocalDateTime;
import java.util.List;


public class Departure {

    private int departureId;

    private int airportId;

    private LocalDateTime departureTime;

    private Airport airport;

    private List<Travel> travel;

    public int getDepartureId() {
        return departureId;
    }

    public int getAirportId() {
        return airportId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public Airport getAirport() {
        return airport;
    }

    public List<Travel> getTravel() {
        return travel;
    }

    protected Departure() {}

    private Departure(Departure.Builder builder) {
        this.departureId = builder.departureId;
        this.airportId = builder.airportId;
        this.departureTime = builder.departureTime;
        this.airport = builder.airport;
        this.travel = builder.travel;
    }

    public static Departure.Builder builder() {
        return new Departure.Builder();
    }

    public static class Builder {

        private int departureId;

        private int airportId;

        private LocalDateTime departureTime;

        private Airport airport;

        private List<Travel> travel;

        public Builder setDepartureId(final int departureId){
            this.departureId = departureId;
            return this;
        }

        public Builder setAirportId(final int airportId){
            this.airportId = airportId;
            return this;
        }

        public Builder setDepartureTime(final LocalDateTime departureTime){
            this.departureTime = departureTime;
            return this;
        }

        public Builder setAirport(final Airport airport){
            this.airport = airport;
            return this;
        }

        public Builder setTravel(final List<Travel> travel){
            this.travel = travel;
            return this;
        }

        public Departure create() {
            return new Departure(this);
        }
    }

    @Override
    public String toString() {
        return "Departure{" +
                "departureId=" + departureId +
                ", airportId=" + airportId +
                ", departureTime=" + departureTime +
                '}';
    }
}
