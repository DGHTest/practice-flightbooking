package com.practice.flightbooking.persistence.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "passenger")
public class PassengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passenger")
    private Integer idPassenger;

    @Column(name = "last_names", length=50, nullable=false)
    private String lastNames;

    @Column(name = "first_name", length=50, nullable=false)
    private String firstName;

    @DateTimeFormat(pattern="yyyy/MM/dd")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(length=100, unique=true, nullable=false)
    private String email;

    @Column(name = "telephone_number", length=15, unique = true, nullable=false)
    private String telephoneNumber;

    @Column(length=50, nullable=false)
    private String country;

    @Column(length=50, nullable=false)
    private String state;

    @Column(length=50, nullable=false)
    private String city;

    @Column(name = "passport_number", unique = true, nullable = false)
    private Long passportNumber;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(length=3, nullable=false)
    private String nationality;

    @Column(nullable=false)
    private Boolean status;

    @OneToMany(mappedBy = "passengerEntity")
    private List<TicketEntity> ticketEntity;

    @OneToMany(mappedBy = "passengerEntity")
    private List<PassengersTravelsEntity> travelsEntities;

    public Integer getIdPassenger() {
        return idPassenger;
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

    public Long getPassportNumber() {
        return passportNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getNationality() {
        return nationality;
    }

    public Boolean getStatus() {
        return status;
    }

    public List<PassengersTravelsEntity> getTravelsEntities() {
        return travelsEntities;
    }

    public List<TicketEntity> getTicketEntity() {
        return ticketEntity;
    }

    protected PassengerEntity() {}

    private PassengerEntity(PassengerEntity.Builder builder) {
        this.idPassenger = builder.idPassenger;
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
        this.travelsEntities = builder.travelsEntities;
        this.ticketEntity = builder.ticketEntity;
        this.status = builder.status;
    }

    public static PassengerEntity.Builder builder() {
        return new PassengerEntity.Builder();
    }

    public static class Builder {

        private Integer idPassenger;
        private String lastNames;
        private String firstName;
        private LocalDate birthDate;
        private String email;
        private String telephoneNumber;
        private String country;
        private String state;
        private String city;
        private Long passportNumber;
        private LocalDate expirationDate;
        private String nationality;
        private List<PassengersTravelsEntity> travelsEntities;
        private List<TicketEntity> ticketEntity;
        private Boolean status = true;

        public Builder setIdPassenger(final Integer idPassenger){
            this.idPassenger = idPassenger;
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

        public Builder setPassportNumber(final Long passportNumber){
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

        public Builder setTravelsEntities(final List<PassengersTravelsEntity> travelsEntities){
            this.travelsEntities = travelsEntities;
            return this;
        }

        public Builder setTicketEntity(final List<TicketEntity> ticketEntity){
            this.ticketEntity = ticketEntity;
            return this;
        }

        public Builder setStatus(final Boolean status){
            this.status = status;
            return this;
        }

        public PassengerEntity create() {
            return new PassengerEntity(this);
        }
    }

    @Override
    public String toString() {
        return "PassengerEntity{" +
                "idPassenger=" + idPassenger +
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
                ", active=" + status +
                '}';
    }
}
