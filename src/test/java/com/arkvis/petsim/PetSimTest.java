package com.arkvis.petsim;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetSimTest {

    @Test
    void should_returnCorrectPetName_when_gettingName() {
        String name = "TEST_NAME";
        Pet pet = new Pet(name, new StubAttribute(), new StubAttribute());
        assertEquals(name, pet.getName());
    }

    @Test
    public void should_returnCorrectStartingHunger_when_creatingPet() {
        int minValue = 0;
        Attribute hunger = new IncreasingAttribute.Builder()
                .minValue(minValue)
                .maxValue(100)
                .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                .increaseAmount(1)
                .build();

        Pet pet = new Pet("TEST_NAME", hunger, new StubAttribute());
        assertEquals(minValue, pet.getHunger());
    }

    @Test
    public void should_returnCorrectStartingHappiness_when_creatingPet() {
        int maxValue = 100;
        Attribute happiness = new DecreasingAttribute.Builder()
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(1)
                .build();

        Pet pet = new Pet("TEST_NAME", new StubAttribute(), happiness);
        assertEquals(maxValue, pet.getHappiness());
    }

}
