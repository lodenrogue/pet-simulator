package com.arkvis.petsim;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HappinessTest {

    @Test
    void should_returnCorrectStartingHappiness_when_creatingPet() {
        int maxValue = 100;
        Attribute happiness = new Attribute.Builder()
                .startingValue(maxValue)
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(1)
                .build();

        Pet pet = new Pet(
                "TEST_NAME",
                new StubAttribute(),
                happiness,
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute());

        assertEquals(maxValue, pet.getHappiness());
    }

    @Test
    void should_increaseHappinessStat_when_playingWithPet() {
        int startingValue = 10;
        int playAmount = 5;
        int increasedHappiness = startingValue + playAmount;

        Attribute happiness = new Attribute.Builder()
                .startingValue(startingValue)
                .minValue(0)
                .maxValue(100)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(1)
                .build();

        Pet pet = new Pet(
                "TEST_NAME",
                new StubAttribute(),
                happiness,
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute());

        pet.play(playAmount);
        assertEquals(increasedHappiness, pet.getHappiness());
    }
}
