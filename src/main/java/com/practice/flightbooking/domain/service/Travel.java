package com.practice.flightbooking.domain.service;

import java.math.BigDecimal;

public class Travel {

    private int travelId;

    private int arrivalFlightId;

    private int departureId;

    private BigDecimal price;

    private ArrivalFlight arrivalFlight;

    private Departure departure;

    public int getTravelId() {
        return travelId;
    }

    public int getArrivalFlightId() {
        return arrivalFlightId;
    }

    public int getDepartureId() {
        return departureId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ArrivalFlight getArrivalFlight() {
        return arrivalFlight;
    }

    public Departure getDeparture() {
        return departure;
    }

    protected Travel() {}

    private Travel(Travel.Builder builder) {
        this.travelId = builder.travelId;
        this.arrivalFlightId = builder.arrivalFlightId;
        this.departureId = builder.departureId;
        this.price = builder.price;
        this.departure = builder.departure;
        this.arrivalFlight = builder.arrivalFlight;
    }

    public static Travel.Builder builder() {
        return new Travel.Builder();
    }

    public static class Builder {

        private int travelId;

        private int arrivalFlightId;

        private int departureId;

        private BigDecimal price;

        private Departure departure;

        private ArrivalFlight arrivalFlight;

        public Builder setTravelId(final int travelId){
            this.travelId = travelId;
            return this;
        }

        public Builder setArrivalFlightId(final int arrivalFlightId){
            this.arrivalFlightId = arrivalFlightId;
            return this;
        }

        public Builder setDepartureId(final int departureId){
            this.departureId = departureId;
            return this;
        }

        public Builder setPrice(final BigDecimal price){
            this.price = price;
            return this;
        }

        public Builder setDeparture(final Departure departure){
            this.departure = departure;
            return this;
        }

        public Builder setArrivalFlight(final ArrivalFlight arrivalFlight){
            this.arrivalFlight = arrivalFlight;
            return this;
        }

        public Travel create() {
            return new Travel(this);
        }
    }

    @Override
    public String toString() {
        return "Travel{" +
                "travelId=" + travelId +
                ", arrivalFlightId=" + arrivalFlightId +
                ", departureId=" + departureId +
                ", price=" + price +
                '}';
    }
}
