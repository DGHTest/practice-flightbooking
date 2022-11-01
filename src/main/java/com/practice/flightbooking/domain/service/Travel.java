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

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public int getArrivalFlightId() {
        return arrivalFlightId;
    }

    public void setArrivalFlightId(int arrivalFlightId) {
        this.arrivalFlightId = arrivalFlightId;
    }

    public int getDepartureId() {
        return departureId;
    }

    public void setDepartureId(int departureId) {
        this.departureId = departureId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ArrivalFlight getArrivalFlight() {
        return arrivalFlight;
    }

    public void setArrivalFlight(ArrivalFlight arrivalFlight) {
        this.arrivalFlight = arrivalFlight;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }
}
