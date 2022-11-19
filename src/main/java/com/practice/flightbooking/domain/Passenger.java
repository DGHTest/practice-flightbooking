package com.practice.flightbooking.domain;


import java.time.LocalDate;
import java.util.List;

public class Passenger {

    private int passengerId;

    private String lastNames;

    private String firstName;

    private LocalDate birthDate;

    private String email;

    private String telephoneNumber;

    private String country;

    private String state;

    private String city;

    private long passportNumber;

    private LocalDate expirationDate;

    private String nationality;

    private List<PassengersTravel> passengersTravel;

    private List<Ticket> tickets;

    public int getPassengerId() {
        return passengerId;
    }

    public String getLastNames() {
        return lastNames;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
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

    public long getPassportNumber() {
        return passportNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getNationality() {
        return nationality;
    }

    public List<PassengersTravel> getPassengersTravel() {
        return passengersTravel;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    protected Passenger() {}

    private Passenger(Passenger.Builder builder) {
        this.passengerId = builder.passengerId;
        this.lastNames = builder.lastNames;
        this.firstName = builder.firstName;
        this.birthDate = builder.birthDate;
        this.email = builder.email;
        this.telephoneNumber = builder.telephoneNumber;
        this.country = builder.country;
        this.state = builder.state;
        this.city = builder.city;
        this.passportNumber = builder.passportNumber;
        this.expirationDate = builder.expirationDate;
        this.nationality = builder.nationality;
        this.passengersTravel = builder.passengersTravel;
        this.tickets = builder.tickets;
    }

    public static Passenger.Builder builder() {
        return new Passenger.Builder();
    }

    public static class Builder {

        private int passengerId;
        private String lastNames;
        private String firstName;
        private LocalDate birthDate;
        private String email;
        private String telephoneNumber;
        private String country;
        private String state;
        private String city;
        private long passportNumber;
        private LocalDate expirationDate;
        private String nationality;
        private List<PassengersTravel> passengersTravel;
        private List<Ticket> tickets;

        public Builder setPassengerId(final int passengerId){
            this.passengerId = passengerId;
            return this;
        }

        public Builder setLastNames(final String lastNames){
            this.lastNames = lastNames;
            return this;
        }

        public Builder setFirstName(final String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder setBirthDate(final LocalDate birthDate){
            this.birthDate = birthDate;
            return this;
        }

        public Builder setEmail(final String email){
            this.email = email;
            return this;
        }

        public Builder setTelephoneNumber(final String telephoneNumber){
            this.telephoneNumber = telephoneNumber;
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

        public Builder setPassportNumber(final long passportNumber){
            this.passportNumber = passportNumber;
            return this;
        }

        public Builder setExpirationDate(final LocalDate expirationDate){
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder setNationality(final String nationality){
            this.nationality = nationality;
            return this;
        }

        public Builder setPassengersTravel(final List<PassengersTravel> passengersTravel){
            this.passengersTravel = passengersTravel;
            return this;
        }

        public Builder setTickets(final List<Ticket> tickets){
            this.tickets = tickets;
            return this;
        }

        public Passenger create() {
            return new Passenger(this);
        }
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", lastNames='" + lastNames + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", passportNumber=" + passportNumber +
                ", expirationDate=" + expirationDate +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
