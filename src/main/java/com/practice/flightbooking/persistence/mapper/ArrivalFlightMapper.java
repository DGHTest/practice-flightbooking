package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.ArrivalFlight;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AirportMapper.class, TravelMapper.class})
public interface ArrivalFlightMapper {

    @Mappings({
            @Mapping(source = "idArrivalFlight", target = "arrivalFlightId"),
            @Mapping(source = "idAirport", target = "airportId"),
            @Mapping(source = "airportEntity", target = "airport"),
            @Mapping(source = "travelEntity", target = "travel")
    })
    ArrivalFlight toArrivalFlight(ArrivalFlightEntity arrivalFlightEntity);

    List<ArrivalFlight> toArrivalFlights(List<ArrivalFlightEntity> arrivalFlightEntities);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    ArrivalFlightEntity toArrivalFlightEntity(ArrivalFlight arrivalFlight);
}
