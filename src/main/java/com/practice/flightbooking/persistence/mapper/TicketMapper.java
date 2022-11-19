package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.Ticket;
import com.practice.flightbooking.persistence.entity.TicketEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    //TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Mappings({
            @Mapping(source = "idTicket", target = "ticketId"),
            @Mapping(source = "idPassenger", target = "passengerId"),
            @Mapping(source = "idTravel", target = "travelId"),
            @Mapping(source = "travelEntity", target = "travel")
    })
    Ticket toTicket(TicketEntity ticketEntity);

    List<Ticket> toTickets(List<TicketEntity> ticketEntities);

    @InheritInverseConfiguration
    @Mapping(target = "passengerEntity", ignore = true)
    TicketEntity toTicketEntity(Ticket ticket);
}

