package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.Travel;
import com.practice.flightbooking.persistence.entity.TravelEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TravelMapper {

    @Mappings({
            @Mapping(source = "idTravel", target = "travelId"),
            @Mapping(source = "departureEntity", target = "departure"),
            @Mapping(source = "arrivalFlightEntity", target = "arrivalFlight")
    })
    Travel toTravel(TravelEntity travelEntity);

    @InheritInverseConfiguration
    @Mapping(target = "passengerEntities", ignore = true)
    TravelEntity toTravelEntity(Travel travel);
}
