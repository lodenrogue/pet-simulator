package com.arkvis.petsim;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HygieneTest {

    @Test
    void should_returnCorrectStartingHygiene_when_creatingPet() {
        int maxValue = 100;
        Attribute hygiene = new Attribute.Builder()
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
                hygiene,
                new StubAttribute(),
                new StubAttribute());

        assertEquals(maxValue, pet.getHygiene());
    }
}
