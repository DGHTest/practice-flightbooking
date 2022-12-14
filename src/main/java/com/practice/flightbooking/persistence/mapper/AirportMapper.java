package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.Airport;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    @Mapping(source = "idAirport", target = "airportId")
    Airport toAirport(AirportEntity airportEntity);

    List<Airport> toAirports(List<AirportEntity> airportEntities);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "icao", ignore = true),
            @Mapping(target = "arrivalFlightEntities", ignore = true),
            @Mapping(target = "departureEntities", ignore = true)
    })
    AirportEntity toAirportEntity(Airport airport);
}

