package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.Passenger;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    //PassengerMapper passengerMapper = Mappers.getMapper(PassengerMapper.class);

    @Mappings({
            @Mapping(source = "idPassenger", target = "passengerId"),
            @Mapping(source = "travelsEntities", target = "passengersTravel"),
            @Mapping(source = "ticketEntity", target = "tickets")
    })
    Passenger toPassenger(PassengerEntity passengerEntity);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    PassengerEntity toPassengerEntity(Passenger passenger);
}
