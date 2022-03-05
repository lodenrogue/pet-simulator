package com.arkvis.petsim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

class AttributeBuilderTest {

    @Test
    void should_throwException_when_buildingAttributeButStartingValueIsLessThanMinValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Attribute.Builder()
                        .startingValue(10)
                        .minValue(20)
                        .maxValue(100)
                        .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                        .decreaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButStartingValueIsGreaterThanMaxValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Attribute.Builder()
                        .startingValue(150)
                        .minValue(20)
                        .maxValue(100)
                        .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                        .decreaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingStartingValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Attribute.Builder()
                        .minValue(0)
                        .maxValue(100)
                        .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                        .decreaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingMinValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Attribute.Builder()
                        .startingValue(100)
                        .maxValue(100)
                        .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                        .decreaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingMaxValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Attribute.Builder()
                        .startingValue(100)
                        .minValue(0)
                        .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                        .decreaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingTimeToIncrement() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Attribute.Builder()
                        .startingValue(100)
                        .minValue(0)
                        .maxValue(100)
                        .decreaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingAttributeButMissingDecreaseAmount() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Attribute.Builder()
                        .startingValue(100)
                        .minValue(0)
                        .maxValue(100)
                        .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                        .build());
    }
}
