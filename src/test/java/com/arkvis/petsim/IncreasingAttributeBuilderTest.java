package com.arkvis.petsim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class IncreasingAttributeBuilderTest {

    @Test
    void should_throwException_when_buildingHungerButMissingMinValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hunger.Builder()
                        .maxValue(100)
                        .timeToIncrement(Duration.of(1, ChronoUnit.MINUTES))
                        .incrementAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingHungerButMissingMaxValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hunger.Builder()
                        .minValue(0)
                        .timeToIncrement(Duration.of(1, ChronoUnit.MINUTES))
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
                        .timeToIncrement(Duration.of(1, ChronoUnit.MINUTES))
                        .build());
    }
}
