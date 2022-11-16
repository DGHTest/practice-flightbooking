package com.practice.flightbooking.domain.service;

public class Airport {

    private int airportId;

    private String country;

    private String state;

    private String city;

    private String iata;

    public int getAirportId() {
        return airportId;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getIata() {
        return iata;
    }

    protected Airport() {}

    private Airport(Airport.Builder builder) {
        this.airportId = builder.airportId;
        this.country = builder.country;
        this.state = builder.state;
        this.city = builder.city;
        this.iata = builder.iata;
    }
    public static Airport.Builder builder() {
        return new Airport.Builder();
    }

    public static class Builder {

        private int airportId;

        private String country;

        private String state;

        private String city;

        private String iata;

        public Builder setAirportId(final int airportId){
            this.airportId = airportId;
            return this;
        }

        public Builder setCountry(final String country){
            this.country = country;
            return this;
        }
        public Builder setState(final String state){
            this.state = state;
            return this;
        }

        public Builder setCity(final String city){
            this.city = city;
            return this;
        }

        public Builder setIata(final String iata){
            this.iata = iata;
            return this;
        }

        public Airport create() {
            return new Airport(this);
        }
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", iata='" + iata + '\'' +
                '}';
    }
}
