package com.arkvis.petsim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

class IncreasingAttributeBuilderTest {

    @Test
    void should_throwException_when_buildingAttributeButMissingStartingValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IncreasingAttribute.Builder()
                        .minValue(0)
                        .maxValue(100)
                        .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                        .increaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingMinValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IncreasingAttribute.Builder()
                        .maxValue(100)
                        .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                        .increaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingMaxValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IncreasingAttribute.Builder()
                        .minValue(0)
                        .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                        .increaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingTimeToIncrement() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IncreasingAttribute.Builder()
                        .minValue(0)
                        .maxValue(100)
                        .increaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingIncrementAmount() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IncreasingAttribute.Builder()
                        .minValue(0)
                        .maxValue(100)
                        .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                        .build());
    }
}
