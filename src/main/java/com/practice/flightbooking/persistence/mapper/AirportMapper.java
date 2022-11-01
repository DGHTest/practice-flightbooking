package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.Airport;
import com.practice.flightbooking.persistence.entity.AirportEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportMapper airportMapper = Mappers.getMapper(AirportMapper.class);

    @Mapping(source = "idAirport", target = "airportId")
    Airport toAirport(AirportEntity airportEntity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "icao", ignore = true),
            @Mapping(target = "arrivalFlightEntities", ignore = true),
            @Mapping(target = "departureEntities", ignore = true)
    })
    AirportEntity toAirportEntity(Airport airport);
}

