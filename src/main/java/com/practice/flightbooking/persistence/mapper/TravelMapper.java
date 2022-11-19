package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.Travel;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring"/*, uses = {ArrivalFlightMapper.class, DepartureMapper.class}*/)
public interface TravelMapper {

    TravelMapper mapper = Mappers.getMapper(TravelMapper.class);

    @Mappings({
            @Mapping(source = "idTravel", target = "travelId"),
            @Mapping(source = "idArrivalFlight", target = "arrivalFlightId"),
            @Mapping(source = "idDeparture", target = "departureId"),
            @Mapping(source = "departureEntity", target = "departure"),
            @Mapping(source = "arrivalFlightEntity", target = "arrivalFlight")
    })
    Travel toTravel(TravelEntity travelEntity);

    List<Travel> toTravels(List<TravelEntity> travelsEntity);

    @InheritInverseConfiguration
    @Mapping(target = "passengersEntities", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "ticketEntities", ignore = true)
    TravelEntity toTravelEntity(Travel travel);
}
