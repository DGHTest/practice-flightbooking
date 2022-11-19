package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.PassengersTravel;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengersTravelMapper {

    //PassengersTravelMapper mapper = Mappers.getMapper(PassengersTravelMapper.class);

    @Mapping(source = "passengerTravelsId.idTravel", target = "travelId")
    PassengersTravel toPassengersTravel(PassengersTravelsEntity passengersTravelsEntity);

    List<PassengersTravel> toPassengersTravels(List<PassengersTravelsEntity> passengersTravelsEntities);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "passengerTravelsId.idPassenger", ignore = true),
            @Mapping(target = "passengerEntity", ignore = true),
            @Mapping(target = "travelEntity", ignore = true)
    })
    PassengersTravelsEntity toPassengersTravelEntity(PassengersTravel passengersTravel);
}
