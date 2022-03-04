package com.arkvis.petsim;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HungerTest {

    @Test
    public void should_returnCorrectStartingHunger_when_creatingPet() {
        int minValue = 0;
        Attribute hunger = createHunger(minValue);

        Pet pet = new Pet("TEST_NAME", hunger, new StubAttribute());
        assertEquals(minValue, pet.getHunger());
    }

    @Test
    public void should_returnIncreasedHunger_when_oneDurationHasPassed() {
        int incrementAmount = 1;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute hunger = new IncreasingAttribute.Builder()
                .minValue(0)
                .maxValue(100)
                .timeToIncrease(time)
                .incrementAmount(1)
                .build();

        Pet pet = new Pet("TEST_NAME", hunger, new StubAttribute());
        pet.progressTime(time);
        assertEquals(incrementAmount, pet.getHunger());
    }

    @Test
    public void should_returnIncreasedHunger_when_multipleTimeDurationsHavePassed() {
        int incrementAmount = 1;
        Attribute hunger = createHunger(0, incrementAmount);
        Pet pet = new Pet("TEST_NAME", hunger, new StubAttribute());

        int numberOfIncrements = 2;
        pet.progressTime(Duration.of(numberOfIncrements, ChronoUnit.MINUTES));

        int expectedAmount = incrementAmount * numberOfIncrements;
        assertEquals(expectedAmount, pet.getHunger());
    }

    @Test
    public void should_returnIncreasedHunger_when_timeIsProgressedMultipleTimes() {
        int incrementAmount = 1;
        Attribute hunger = createHunger(0, incrementAmount);
        Pet pet = new Pet("TEST_NAME", hunger, new StubAttribute());

        pet.progressTime(Duration.of(1, ChronoUnit.MINUTES));
        pet.progressTime(Duration.of(1, ChronoUnit.MINUTES));

        int numberOfIncrements = 2;
        int expectedAmount = incrementAmount * numberOfIncrements;
        assertEquals(expectedAmount, pet.getHunger());
    }

    @Test
    void should_returnMaxHunger_when_tooMuchTimeHasPassed() {
        int maxValue = 100;
        Attribute hunger = new IncreasingAttribute.Builder()
                .minValue(0)
                .maxValue(maxValue)
                .timeToIncrease(Duration.of(1, ChronoUnit.SECONDS))
                .incrementAmount(1)
                .build();

        Pet pet = new Pet("TEST_NAME", hunger, new StubAttribute());
        pet.progressTime(Duration.of(10_000, ChronoUnit.SECONDS));
        assertEquals(maxValue, pet.getHunger());
    }

    private Attribute createHunger(int minValue, int incrementAmount) {
        return new IncreasingAttribute.Builder()
                .minValue(minValue)
                .maxValue(100)
                .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                .incrementAmount(incrementAmount)
                .build();
    }

    private Attribute createHunger(int minValue) {
        return createHunger(minValue, 1);
    }
}
