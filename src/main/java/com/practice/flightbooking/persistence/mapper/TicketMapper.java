package com.practice.flightbooking.persistence.mapper;

import com.practice.flightbooking.domain.service.Ticket;
import com.practice.flightbooking.persistence.entity.TicketEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mappings({
            @Mapping(source = "idTicket", target = "ticketId"),
            @Mapping(source = "idPassenger", target = "passengerId")
    })
    Ticket toTicket(TicketEntity ticketEntity);

    @InheritInverseConfiguration
    @Mapping(target = "passengerEntity", ignore = true)
    TicketEntity toTicketEntity(Ticket ticket);
}
