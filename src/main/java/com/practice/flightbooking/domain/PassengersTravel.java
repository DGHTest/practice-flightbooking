package com.practice.flightbooking.domain;


public class PassengersTravel {

    private int travelId;

    public int getTravelId() {
        return travelId;
    }

    protected PassengersTravel() {}

    private PassengersTravel(PassengersTravel.Builder builder) {
        this.travelId = builder.travelId;
    }

    public static PassengersTravel.Builder builder() {
        return new PassengersTravel.Builder();
    }

    public static class Builder {

        private int travelId;

        public Builder setTravelId(final int travelId){
            this.travelId = travelId;
            return this;
        }

        public PassengersTravel create() {
            return new PassengersTravel(this);
        }
    }

    @Override
    public String toString() {
        return "PassengersTravel{" +
                "travelsPassengerId=" + travelId +
                '}';
    }
}
