package com.arkvis.petsim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class HungerTest {

    @Test
    void should_throwException_when_buildingHungerButMissingMinValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hunger.Builder()
                        .maxValue(100)
                        .timeToIncrement(new Time(1, TimeUnit.SECONDS))
                        .incrementAmount(1)
                        .build());
    }

    @Test
    void should_throwException_when_buildingHungerButMissingMaxValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hunger.Builder()
                        .minValue(0)
                        .timeToIncrement(new Time(1, TimeUnit.SECONDS))
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
                        .timeToIncrement(new Time(1, TimeUnit.SECONDS))
                        .build());
    }
}
