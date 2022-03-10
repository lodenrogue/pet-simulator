package com.arkvis.petsim;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetSimTest {

    @Test
    void should_returnCorrectPetName_when_gettingName() {
        String name = "TEST_NAME";
        Pet pet = new Pet(
                name,
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute());

        assertEquals(name, pet.getName());
    }

    @Test
    void should_returnCorrectStartingBladder_when_creatingPet() {
        int minValue = 0;
        Attribute bladder = new Attribute.Builder()
                .startingValue(minValue)
                .minValue(minValue)
                .maxValue(100)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(1)
                .build();

        Pet pet = new Pet(
                "TEST_NAME",
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute(),
                bladder,
                new StubAttribute());

        assertEquals(minValue, pet.getBladder());
    }

    @Test
    void should_returnCorrectStartingEnergy_when_creatingPet() {
        int maxValue = 100;
        Attribute energy = new Attribute.Builder()
                .startingValue(maxValue)
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(1)
                .build();

        Pet pet = new Pet(
                "TEST_NAME",
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute(),
                new StubAttribute(),
                energy);

        assertEquals(maxValue, pet.getEnergy());
    }

    @Test
    void should_returnCorrectValues_when_progressingTime() {
        int minValue = 0;
        int maxValue = 100;
        int changeAmount = 1;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute hunger = new SimpleAttribute(time);
        Attribute happiness = new SimpleAttribute(time);
        Attribute hygiene = new SimpleAttribute(time);
        Attribute bladder = new SimpleAttribute(time);
        Attribute energy = new SimpleAttribute(time);

        Pet pet = new Pet(
                "TEST_NAME",
                hunger,
                happiness,
                hygiene,
                bladder,
                energy);

        pet.progressTime(time);

        int expectedValue = maxValue - changeAmount;
        assertEquals(expectedValue, pet.getHunger());
        assertEquals(expectedValue, pet.getHappiness());
        assertEquals(expectedValue, pet.getHygiene());
        assertEquals(expectedValue, pet.getBladder());
        assertEquals(expectedValue, pet.getEnergy());
    }
}
