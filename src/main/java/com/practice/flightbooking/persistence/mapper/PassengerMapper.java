package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.Passenger;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    @Mapping(source = "idPassenger", target = "passengerId")
    @Mapping(source = "travelEntity", target = "travel")
    Passenger toPassenger(PassengerEntity passengerEntity);

    @InheritInverseConfiguration
    @Mapping(target = "ticketEntity", ignore = true)
    PassengerEntity toPassengerEntity(Passenger passenger);
}
