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
    void should_returnCorrectStartingHunger_when_creatingPet() {
        int minValue = 0;
        Attribute hunger = new IncreasingAttribute.Builder()
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
    void should_returnCorrectStartingHappiness_when_creatingPet() {
        int maxValue = 100;
        Attribute happiness = new DecreasingAttribute.Builder()
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
    void should_returnCorrectStartingHygiene_when_creatingPet() {
        int maxValue = 100;
        Attribute hygiene = new DecreasingAttribute.Builder()
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(1)
                .build();

        Pet pet = new Pet(
                "TEST_NAME",
                new StubAttribute(),
                new StubAttribute(),
                hygiene,
                new StubAttribute(),
                new StubAttribute());

        assertEquals(maxValue, pet.getHygiene());
    }

    @Test
    void should_returnCorrectStartingBladder_when_creatingPet() {
        int minValue = 0;
        Attribute bladder = new IncreasingAttribute.Builder()
                .minValue(minValue)
                .maxValue(100)
                .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                .increaseAmount(1)
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
        Attribute energy = new DecreasingAttribute.Builder()
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

        Attribute hunger = new SimpleIncreasingAttribute(time);
        Attribute happiness = new SimpleDecreasingAttribute(time);
        Attribute hygiene = new SimpleDecreasingAttribute(time);
        Attribute bladder = new SimpleIncreasingAttribute(time);
        Attribute energy = new SimpleDecreasingAttribute(time);

        Pet pet = new Pet(
                "TEST_NAME",
                hunger,
                happiness,
                hygiene,
                bladder,
                energy);

        pet.progressTime(time);

        assertEquals(minValue + changeAmount, pet.getHunger());
        assertEquals(maxValue - changeAmount, pet.getHappiness());
        assertEquals(maxValue - changeAmount, pet.getHygiene());
        assertEquals(minValue + changeAmount, pet.getBladder());
        assertEquals(maxValue - changeAmount, pet.getEnergy());
    }
}
