package com.arkvis.petsim;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttributeTest {

    @Test
    public void should_returnCorrectStartingValue_when_creatingAttribute() {
        int maxValue = 100;
        Attribute attribute = new Attribute.Builder()
                .startingValue(maxValue)
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(Duration.of(1, ChronoUnit.MINUTES))
                .decreaseAmount(1)
                .build();
        assertEquals(maxValue, attribute.getValue());
    }

    @Test
    public void should_returnDecreasedValue_when_oneDurationHasPassed() {
        int decreaseAmount = 1;
        int maxValue = 100;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute attribute = new Attribute.Builder()
                .startingValue(maxValue)
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(time)
                .decreaseAmount(decreaseAmount)
                .build();

        attribute.progressTime(time);
        int expectedValue = maxValue - decreaseAmount;
        assertEquals(expectedValue, attribute.getValue());
    }

    @Test
    public void should_returnDecreasedValue_when_multipleTimeDurationsHavePassed() {
        int decreaseAmount = 1;
        int maxValue = 100;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute attribute = new Attribute.Builder()
                .startingValue(maxValue)
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(time)
                .decreaseAmount(decreaseAmount)
                .build();

        int numberOfDecrements = 2;
        attribute.progressTime(time.multipliedBy(numberOfDecrements));

        int expectedAmount = maxValue - (decreaseAmount * numberOfDecrements);
        assertEquals(expectedAmount, attribute.getValue());
    }

    @Test
    public void should_returnDecreasedValue_when_timeIsProgressedMultipleTimes() {
        int decreaseAmount = 1;
        int maxValue = 100;
        Duration time = Duration.of(1, ChronoUnit.MINUTES);

        Attribute attribute = new Attribute.Builder()
                .startingValue(maxValue)
                .minValue(0)
                .maxValue(maxValue)
                .timeToDecrease(time)
                .decreaseAmount(decreaseAmount)
                .build();

        attribute.progressTime(time);
        attribute.progressTime(time);

        int numberOfDecrements = 2;
        int expectedAmount = maxValue - (decreaseAmount * numberOfDecrements);
        assertEquals(expectedAmount, attribute.getValue());
    }

    @Test
    void should_returnMinValue_when_tooMuchTimeHasPassed() {
        int minValue = 0;
        Duration time = Duration.of(1, ChronoUnit.SECONDS);

        Attribute attribute = new Attribute.Builder()
                .startingValue(100)
                .minValue(minValue)
                .maxValue(100)
                .timeToDecrease(time)
                .decreaseAmount(1)
                .build();

        attribute.progressTime(time.multipliedBy(10_000));
        assertEquals(minValue, attribute.getValue());
    }
}
