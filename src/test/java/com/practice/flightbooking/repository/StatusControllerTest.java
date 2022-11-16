package com.practice.flightbooking.repository;


import com.practice.flightbooking.persistence.schedule.StatusController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@SpringBootTest
class StatusControllerTest {

    @SpyBean
    private StatusController statusController;

    @Test
    @DisplayName("Should be executed one time when the 20 minutes happen")
    void arrivalStatus() {
        await()
                .atMost(Duration.ofMinutes(20))
                .untilAsserted(() -> verify(statusController, times(1)).arrivalAndTravelStatus());
    }

    @Test
    @DisplayName("Should be executed one time when the 20 minutes happen")
    void departureStatus() {
        await()
                .atMost(Duration.ofMinutes(20))
                .untilAsserted(() -> verify(statusController, times(1)).departureStatus());
    }
}