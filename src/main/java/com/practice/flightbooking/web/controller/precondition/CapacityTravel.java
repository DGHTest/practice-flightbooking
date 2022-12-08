package com.practice.flightbooking.web.controller.precondition;

import com.practice.flightbooking.persistence.crud.PassengersTravelsCrudRepository;
import com.practice.flightbooking.persistence.crud.TravelCrudRepository;
import com.practice.flightbooking.persistence.entity.PassengersTravelsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CapacityTravel {

    @Autowired
    private PassengersTravelsCrudRepository passengersTravelsCrudRepository;

    @Autowired
    private TravelCrudRepository travelCrudRepository;

    public boolean capacity(int travelId) {
        Optional<List<PassengersTravelsEntity>> ptSize = passengersTravelsCrudRepository.findByPassengerTravelsIdIdTravel(travelId);

        if (ptSize.isPresent()) {
            if (ptSize.get().size() < 200) {
                return true;
            } else {
                travelCrudRepository.updateTravelStatus(travelId);
                return false;
            }
        }

        return  true;
    }
}
