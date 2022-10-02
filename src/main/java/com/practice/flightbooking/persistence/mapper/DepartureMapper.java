package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.Departure;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartureMapper {

    @Mapping(source = "idDepartureFlight", target = "departureFlightId")
    @Mapping(source = "airportEntity", target = "airport")
    Departure toDeparture(DepartureEntity departureEntity);

    @InheritInverseConfiguration
    @Mapping(target = "travelEntity", ignore = true)
    DepartureEntity toDepartureEntity(Departure departure);
}
