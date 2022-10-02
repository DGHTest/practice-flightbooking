package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.Airport;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    @Mapping(source = "idAirport", target = "airportId")
    Airport toAirport(AirportEntity airportEntity);

    @InheritInverseConfiguration
    @Mapping(target = "arrivalFlightEntities", ignore = true)
    @Mapping(target = "departureFlightEntities", ignore = true)
    AirportEntity toAirportEntity(Airport airport);
}
