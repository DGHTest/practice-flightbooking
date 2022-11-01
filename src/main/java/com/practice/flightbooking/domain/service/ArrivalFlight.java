package com.practice.flightbooking.domain.service;

import java.time.LocalDateTime;
import java.util.List;


public class ArrivalFlight {

    private int arrivalFlightId;

    private int airportId;

    private LocalDateTime arrivalTime;

    private Airport airport;

    private List<Travel> travel;

    public int getArrivalFlightId() {
        return arrivalFlightId;
    }

    public void setArrivalFlightId(int arrivalFlightId) {
        this.arrivalFlightId = arrivalFlightId;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public List<Travel> getTravel() {
        return travel;
    }

    public void setTravel(List<Travel> travel) {
        this.travel = travel;
    }
}
