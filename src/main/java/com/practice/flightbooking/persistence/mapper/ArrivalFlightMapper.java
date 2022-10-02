package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.ArrivalFlight;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArrivalFlightMapper {

    @Mapping(source = "idArrivalFlight", target = "arrivalFlightId")
    @Mapping(source = "airportEntity", target = "airport")
    ArrivalFlight toArrivalFlight(ArrivalFlightEntity arrivalFlightEntity);

    @InheritInverseConfiguration
    @Mapping(target = "travelEntity", ignore = true)
    ArrivalFlightEntity toArrivalFlightEntity(ArrivalFlight arrivalFlight);
}
