package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.Departure;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AirportMapper.class, TravelMapper.class})
public interface DepartureMapper {

    @Mappings({
            @Mapping(source = "idDeparture", target = "departureFlightId"),
            @Mapping(source = "idAirport", target = "airportId"),
            @Mapping(source = "airportEntity", target = "airport"),
            @Mapping(source = "travelEntity", target = "travel")
    })
    Departure toDeparture(DepartureEntity departureEntity);

    List<Departure> toDepartures(List<DepartureEntity> departureEntities);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    DepartureEntity toDepartureEntity(Departure departure);
}
