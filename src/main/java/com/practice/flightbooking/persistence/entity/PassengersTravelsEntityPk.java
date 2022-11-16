package com.practice.flightbooking.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PassengersTravelsEntityPk implements Serializable {

    @Column(name = "id_passenger", nullable=false)
    private Integer idPassenger;

    @Column(name = "id_travel", nullable=false)
    private Integer idTravel;

    public Integer getIdPassenger() {
        return idPassenger;
    }

    public Integer getIdTravel() {
        return idTravel;
    }

    protected PassengersTravelsEntityPk() {}

    private PassengersTravelsEntityPk(PassengersTravelsEntityPk.Builder builder) {
        this.idPassenger = builder.idPassenger;
        this.idTravel = builder.idTravel;
    }

    public static PassengersTravelsEntityPk.Builder builder() {
        return new PassengersTravelsEntityPk.Builder();
    }

    public static class Builder {
        private Integer idPassenger;

        private Integer idTravel;

        public Builder setIdPassenger(final Integer idPassenger){
            this.idPassenger = idPassenger;
            return this;
        }

        public Builder setIdTravel(final Integer idTravel){
            this.idTravel = idTravel;
            return this;
        }


        public PassengersTravelsEntityPk create() {
            return new PassengersTravelsEntityPk(this);
        }
    }

    @Override
    public String toString() {
        return "PassengersTravelsEntityPk{" +
                "idPassenger=" + idPassenger +
                ", idTravel=" + idTravel +
                '}';
    }
}
