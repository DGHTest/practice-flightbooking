package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.PassengersTravel;
import com.practice.flightbooking.persistence.entity.PassengersTravelEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {TravelMapper.class})
public interface PassengersTravelMapper {

    @Mapping(source = "id.idTravel", target = "travelId")
    PassengersTravel toPassengersTravel(PassengersTravelEntity passengersTravelEntity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "passengerEntities", ignore = true),
            @Mapping(target = "travelEntity", ignore = true),
            @Mapping(target = "id.idPassenger", ignore = true)
    })
    PassengersTravelEntity toPassengersTravelEntity(PassengersTravel passengersTravel);
}
