package com.arkvis.petsim;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HappinessTest {

    @Test
    public void should_returnCorrectStartingHappiness_when_creatingPet() {
        int maxValue = 100;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute happiness = new DecreasingAttribute.Builder()
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(time)
                .decreaseAmount(1)
                .build();

        Pet pet = new Pet(
                "TEST_NAME",
                new StubAttribute(),
                happiness);
        assertEquals(maxValue, pet.getHappiness());
    }
}
