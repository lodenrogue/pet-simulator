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

    @Test
    public void should_returnDecreasedHappiness_when_oneDurationHasPassed() {
        int decreaseAmount = 1;
        int maxValue = 100;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute happiness = new DecreasingAttribute.Builder()
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(time)
                .decreaseAmount(decreaseAmount)
                .build();

        Pet pet = new Pet("TEST_NAME", new StubAttribute(), happiness);
        pet.progressTime(time);

        int expectedValue = maxValue - decreaseAmount;
        assertEquals(expectedValue, pet.getHappiness());
    }

    @Test
    public void should_returnDecreasedHappiness_when_multipleTimeDurationsHavePassed() {
        int decreaseAmount = 1;
        int maxValue = 100;
        Attribute happiness = new DecreasingAttribute.Builder()
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(decreaseAmount)
                .build();

        Pet pet = new Pet("TEST_NAME", new StubAttribute(), happiness);

        int numberOfDecrements = 2;
        pet.progressTime(Duration.of(numberOfDecrements, ChronoUnit.MINUTES));

        int expectedAmount = maxValue - (decreaseAmount * numberOfDecrements);
        assertEquals(expectedAmount, pet.getHappiness());
    }

    @Test
    public void should_returnDecreasedHappiness_when_timeIsProgressedMultipleTimes() {
        int decreaseAmount = 1;
        int maxValue = 100;
        Attribute happiness = new DecreasingAttribute.Builder()
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(decreaseAmount)
                .build();

        Pet pet = new Pet("TEST_NAME", new StubAttribute(), happiness);
        pet.progressTime(Duration.of(1, ChronoUnit.MINUTES));
        pet.progressTime(Duration.of(1, ChronoUnit.MINUTES));

        int numberOfDecrements = 2;
        int expectedAmount = maxValue - (decreaseAmount * numberOfDecrements);
        assertEquals(expectedAmount, pet.getHappiness());
    }
}
