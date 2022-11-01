package com.practice.flightbooking.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "passengers_travels")
public class PassengersTravelsEntity {

    @EmbeddedId
    private PassengersTravelsEntityPk passengerTravelsId;

    @ManyToOne
    @JoinColumn(name = "id_passenger", insertable = false, updatable = false)
    private PassengerEntity passengerEntity;

    @ManyToOne
    @JoinColumn(name = "id_travel", insertable = false, updatable = false)
    private TravelEntity travelEntity;

    public PassengersTravelsEntityPk getPassengerTravelsId() {
        return passengerTravelsId;
    }

    public PassengerEntity getPassengerEntity() {
        return passengerEntity;
    }

    public TravelEntity getTravelEntity() {
        return travelEntity;
    }

    protected PassengersTravelsEntity() {}

    private PassengersTravelsEntity(PassengersTravelsEntity.Builder builder) {
        this.passengerTravelsId = builder.passengerTravelsId;
        this.passengerEntity = builder.passengerEntity;
        this.travelEntity = builder.travelEntity;
    }

    public static PassengersTravelsEntity.Builder builder() {
        return new PassengersTravelsEntity.Builder();
    }

    public static class Builder {

        private PassengersTravelsEntityPk passengerTravelsId;

        private PassengerEntity passengerEntity;

        private TravelEntity travelEntity;

        public Builder setPassengerTravelsId(final PassengersTravelsEntityPk passengerTravelsId){
            this.passengerTravelsId = passengerTravelsId;
            return this;
        }

        public Builder setPassengerEntity(final PassengerEntity passengerEntity){
            this.passengerEntity = passengerEntity;
            return this;
        }

        public Builder setTravelEntity(final TravelEntity travelEntity){
            this.travelEntity = travelEntity;
            return this;
        }

        public PassengersTravelsEntity create() {
            validate();
            return new PassengersTravelsEntity(this);
        }

        private void validate() {

        }
    }
}
