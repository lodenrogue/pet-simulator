package com.arkvis.petsim;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HungerTest {

    @Test
    void should_returnCorrectStartingHunger_when_creatingPet() {
        int minValue = 0;
        Attribute hunger = new IncreasingAttribute.Builder()
                .startingValue(minValue)
                .minValue(minValue)
                .maxValue(100)
                .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                .increaseAmount(1)
                .build();

        Pet pet = new Pet(
                "TEST_NAME",
                hunger,
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute());

        assertEquals(minValue, pet.getHunger());
    }

    @Test
    void should_reduceHunger_when_feedingPet() {
        int startingHunger = 20;
        int feedAmount = 5;
        int reducedHunger = startingHunger - feedAmount;

        Attribute hunger = new IncreasingAttribute.Builder()
                .minValue(0)
                .maxValue(100)
                .startingValue(startingHunger)
                .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                .increaseAmount(1)
                .build();

        Pet pet = new Pet(
                "TEST_NAME",
                hunger,
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute());

        pet.feed(feedAmount);
        assertEquals(reducedHunger, pet.getHunger());
    }
}
