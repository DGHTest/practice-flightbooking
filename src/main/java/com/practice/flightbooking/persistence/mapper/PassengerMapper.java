package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = PassengersTravelMapper.class)
public interface PassengerMapper {

    @Mappings({
            @Mapping(source = "idPassenger", target = "passengerId"),
            @Mapping(source = "travelsEntities", target = "passengersTravel")
    })
    Passenger toPassenger(PassengerEntity passengerEntity);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "ticketEntity", ignore = true)
    PassengerEntity toPassengerEntity(Passenger passenger);
}
