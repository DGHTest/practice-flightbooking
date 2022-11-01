package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.PassengersTravel;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring"/*, uses = {TravelMapper.class}*/)
public interface PassengerTravelsMapper {

    //PassengerTravelsMapper mapper = Mappers.getMapper(PassengerTravelsMapper.class);
    @Mapping(source = "passengerTravelsId.idTravel", target = "travelsPassengerId")
    PassengersTravel toPassengersTravel(PassengersTravelsEntity passengersTravelsEntity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "passengerTravelsId.idPassenger", ignore = true),
            @Mapping(target = "passengerEntity", ignore = true),
            @Mapping(target = "travelEntity", ignore = true)
    })
    PassengersTravelsEntity toPassengersTravelEntity(PassengersTravel passengersTravel);
}
