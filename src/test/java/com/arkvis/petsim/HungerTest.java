package com.arkvis.petsim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HungerTest {

    @Test
    void should_throwException_when_buildingHungerButMissingMinValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hunger.Builder()
                        .maxValue(100)
                        .timeToIncrement(new Time(1, TimeUnit.SECONDS))
                        .incrementAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingHungerButMissingMaxValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hunger.Builder()
                        .minValue(0)
                        .timeToIncrement(new Time(1, TimeUnit.SECONDS))
                        .incrementAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingHungerButMissingTimeToIncrement() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hunger.Builder()
                        .minValue(0)
                        .maxValue(100)
                        .incrementAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingHungerButMissingIncrementAmount() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hunger.Builder()
                        .minValue(0)
                        .maxValue(100)
                        .timeToIncrement(new Time(1, TimeUnit.SECONDS))
                        .build());
    }

    @Test
    public void should_returnCorrectStartingHunger_when_creatingPet() {
        int minValue = 0;
        Hunger hunger = createHunger(minValue);

        Pet pet = new Pet("TEST_NAME", hunger);
        assertEquals(minValue, pet.getHunger());
    }

    @Test
    public void should_returnIncreasedHunger_when_oneTimeUnitHasPassed() {
        int incrementAmount = 1;
        Time time = new Time(1, TimeUnit.MINUTES);

        Hunger hunger = new Hunger.Builder()
                .minValue(0)
                .maxValue(100)
                .timeToIncrement(time)
                .incrementAmount(1)
                .build();

        Pet pet = new Pet("TEST_NAME", hunger);
        pet.progressTime(time);
        assertEquals(incrementAmount, pet.getHunger());
    }

    @Test
    public void should_returnIncreasedHunger_when_MultipleTimeUnitsHavePassed() {
        int incrementAmount = 1;

        Hunger hunger = new Hunger.Builder()
                .minValue(0)
                .maxValue(100)
                .timeToIncrement(new Time(1, TimeUnit.MINUTES))
                .incrementAmount(1)
                .build();

        Pet pet = new Pet("TEST_NAME", hunger);

        int numOfTimesUnits = 2;
        Time progressedTime = new Time(numOfTimesUnits, TimeUnit.MINUTES);
        pet.progressTime(progressedTime);

        int expectedAmount = incrementAmount * numOfTimesUnits;
        assertEquals(expectedAmount, pet.getHunger());
    }

    private Hunger createHunger(int minValue) {
        return new Hunger.Builder()
                .minValue(minValue)
                .maxValue(100)
                .timeToIncrement(new Time(1, TimeUnit.SECONDS))
                .incrementAmount(1)
                .build();
    }
}
