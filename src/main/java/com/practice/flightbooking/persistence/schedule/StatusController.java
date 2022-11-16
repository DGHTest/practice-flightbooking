package com.practice.flightbooking.persistence.schedule;

import com.practice.flightbooking.persistence.crud.ArrivalCrudRepository;
import com.practice.flightbooking.persistence.crud.DepartureCrudRepository;
import com.practice.flightbooking.persistence.crud.TravelCrudRepository;
import com.practice.flightbooking.persistence.entity.ArrivalFlightEntity;
import com.practice.flightbooking.persistence.entity.DepartureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class StatusController {

    @Autowired
    private ArrivalCrudRepository arrivalCrudRepository;

    @Autowired
    private DepartureCrudRepository departureCrudRepository;

    @Autowired
    private TravelCrudRepository travelCrudRepository;

    @Scheduled(fixedRateString = "PT20M")
    public void arrivalAndTravelStatus() {
        List<ArrivalFlightEntity> allArrivals = arrivalCrudRepository.findByStatus(true);

        allArrivals.stream().filter(time ->
                        LocalDateTime.now().until(time.getArrivalTime(),
                                ChronoUnit.HOURS) <= TimeUnit.HOURS.toHours(32) ||
                                time.getArrivalTime().isBefore(LocalDateTime.now()))
                .forEach(arrival -> {
                    arrivalCrudRepository.updateArrivalStatus(arrival.getIdArrivalFlight());
                    travelCrudRepository.updateTravelStatus(arrival.getIdArrivalFlight());
                });
    }

    @Scheduled(fixedRateString = "PT20M")
    public void departureStatus() {
        List<DepartureEntity> allDepartures = departureCrudRepository.findByStatus(true);

        allDepartures.stream().filter(time ->
                        LocalDateTime.now().until(time.getDepartureTime(),
                                ChronoUnit.HOURS) <= TimeUnit.HOURS.toHours(32) ||
                                time.getDepartureTime().isBefore(LocalDateTime.now()))
                .forEach(departure -> departureCrudRepository.updateDepartureStatus(departure.getIdDeparture()));
    }
}
