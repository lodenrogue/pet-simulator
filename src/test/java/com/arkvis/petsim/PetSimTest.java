package com.arkvis.petsim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetSimTest {
    private String name;
    private Hunger hunger;

    @BeforeEach
    void setUp() {
        name = "TEST_NAME";
        hunger = createHunger();
    }

    @Test
    void should_returnCorrectPetName_when_gettingName() {
        Pet pet = new Pet(name, hunger);
        assertEquals(name, pet.getName());
    }

    private Hunger createHunger() {
        return new Hunger.Builder()
                .minValue(0)
                .maxValue(100)
                .timeToIncrement(Duration.of(1, ChronoUnit.MINUTES))
                .incrementAmount(1)
                .build();
    }
}
