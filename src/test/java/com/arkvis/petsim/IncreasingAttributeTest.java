package com.arkvis.petsim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncreasingAttributeTest {

    @Test
    void should_throwException_when_startingValueIsLessThanMinValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IncreasingAttribute.Builder()
                        .startingValue(0)
                        .minValue(10)
                        .maxValue(100)
                        .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                        .increaseAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_startingValueIsGreaterThanMaxValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IncreasingAttribute.Builder()
                        .startingValue(150)
                        .minValue(0)
                        .maxValue(100)
                        .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                        .increaseAmount(1)
                        .build());
    }

    @Test
    void should_returnCorrectStartingValue_when_creatingAttribute() {
        int minValue = 0;
        Attribute attribute = new IncreasingAttribute.Builder()
                .startingValue(minValue)
                .minValue(minValue)
                .maxValue(100)
                .timeToIncrease(Duration.of(1, ChronoUnit.MINUTES))
                .increaseAmount(1)
                .build();
        assertEquals(minValue, attribute.getValue());
    }

    @Test
    void should_returnIncreasedValue_when_oneDurationHasPassed() {
        int increaseAmount = 1;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute attribute = new IncreasingAttribute.Builder()
                .startingValue(0)
                .minValue(0)
                .maxValue(100)
                .timeToIncrease(time)
                .increaseAmount(1)
                .build();

        attribute.progressTime(time);
        assertEquals(increaseAmount, attribute.getValue());
    }

    @Test
    void should_returnIncreasedValue_when_multipleTimeDurationsHavePassed() {
        int increaseAmount = 1;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute attribute = new IncreasingAttribute.Builder()
                .startingValue(0)
                .minValue(0)
                .maxValue(100)
                .timeToIncrease(time)
                .increaseAmount(increaseAmount)
                .build();

        int numberOfIncrements = 2;
        attribute.progressTime(time.multipliedBy(numberOfIncrements));

        int expectedAmount = increaseAmount * numberOfIncrements;
        assertEquals(expectedAmount, attribute.getValue());
    }

    @Test
    void should_returnIncreasedValue_when_timeIsProgressedMultipleTimes() {
        int increaseAmount = 1;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute attribute = new IncreasingAttribute.Builder()
                .startingValue(0)
                .minValue(0)
                .maxValue(100)
                .timeToIncrease(time)
                .increaseAmount(increaseAmount)
                .build();

        attribute.progressTime(time);
        attribute.progressTime(time);

        int numberOfIncrements = 2;
        int expectedAmount = increaseAmount * numberOfIncrements;
        assertEquals(expectedAmount, attribute.getValue());
    }

    @Test
    void should_returnMaxHunger_when_tooMuchTimeHasPassed() {
        int maxValue = 100;
        Duration time = Duration.of(1, ChronoUnit.SECONDS);

        Attribute attribute = new IncreasingAttribute.Builder()
                .startingValue(0)
                .minValue(0)
                .maxValue(maxValue)
                .timeToIncrease(time)
                .increaseAmount(1)
                .build();

        attribute.progressTime(time.multipliedBy(10_000));
        assertEquals(maxValue, attribute.getValue());
    }
}
