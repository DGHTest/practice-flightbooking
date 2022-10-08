package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.Passenger;
import com.practice.flightbooking.persistence.entity.PassengerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {PassengersTravelMapper.class, TicketMapper.class})
public interface PassengerMapper {

    @Mappings({
            @Mapping(source = "idPassenger", target = "passengerId"),
            @Mapping(source = "idTravel", target = "travelId"),
            @Mapping(source = "passengersTravelEntities", target = "passengersTravel"),
            @Mapping(source = "ticketEntity", target = "tickets")
    })
    Passenger toPassenger(PassengerEntity passengerEntity);

    @InheritInverseConfiguration
    PassengerEntity toPassengerEntity(Passenger passenger);
}
