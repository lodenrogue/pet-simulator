package com.arkvis.petsim;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HungerTest {

    @Test
    void should_returnCorrectStartingHunger_when_creatingPet() {
        int maxValue = 100;
        Attribute hunger = new Attribute.Builder()
                .startingValue(maxValue)
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(1)
                .build();

        Pet pet = new Pet(
                "TEST_NAME",
                hunger,
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute());

        assertEquals(maxValue, pet.getHunger());
    }

    @Test
    void should_increaseHungerStat_when_feedingPet() {
        int startingHunger = 20;
        int feedAmount = 5;
        int reducedHunger = startingHunger + feedAmount;

        Attribute hunger = new Attribute.Builder()
                .startingValue(startingHunger)
                .minValue(0)
                .maxValue(100)
                .startingValue(startingHunger)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(1)
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
