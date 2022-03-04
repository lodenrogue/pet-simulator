package com.arkvis.petsim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

class DecreasingAttributeBuilderTest {

    @Test
    void should_throwException_when_buildingAttributeButMissingMinValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new DecreasingAttribute.Builder()
                        .maxValue(100)
                        .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                        .decreaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingMaxValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new DecreasingAttribute.Builder()
                        .minValue(0)
                        .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                        .decreaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingTimeToIncrement() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new DecreasingAttribute.Builder()
                        .minValue(0)
                        .maxValue(100)
                        .decreaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingdecreaseAmount() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new DecreasingAttribute.Builder()
                        .minValue(0)
                        .maxValue(100)
                        .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                        .build());
    }
}
