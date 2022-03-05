package com.arkvis.petsim;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Attribute {
    private final int minValue;
    private final int maxValue;
    private final Duration timeToDecrease;
    private final int decreaseAmount;

    private long value;
    private Duration timeSinceDecrease;

    Attribute(int startingValue, int minValue, int maxValue, Duration timeToDecrease, int decreaseAmount) {
        if (startingValue < minValue) {
            throw new IllegalArgumentException("Starting value cannot be less than min value");
        }

        if (startingValue > maxValue) {
            throw new IllegalArgumentException("Starting value cannot be great than max value");
        }

        this.minValue = minValue;
        this.maxValue = maxValue;

        this.timeToDecrease = timeToDecrease;
        this.decreaseAmount = decreaseAmount;

        value = startingValue;
        timeSinceDecrease = Duration.of(0, ChronoUnit.MINUTES);
    }

    long getValue() {
        return value;
    }

    void progressTime(Duration timePassed) {
        timeSinceDecrease = timeSinceDecrease.plus(timePassed);

        if (timeSinceDecrease.compareTo(timeToDecrease) >= 0) {
            long numOfDecrements = timeSinceDecrease.dividedBy(timeToDecrease);
            value = calculateNewValue(numOfDecrements);

            Duration totalDecrementTime = getTotalDecrementTime(numOfDecrements);
            timeSinceDecrease = timeSinceDecrease.minus(totalDecrementTime);
        }
    }

    void increase(int amount) {
        value = Math.min(maxValue, value + amount);
    }

    private Duration getTotalDecrementTime(long numOfDecrements) {
        long totalDecrementsInSeconds = timeToDecrease.getSeconds() * numOfDecrements;
        return Duration.of(totalDecrementsInSeconds, ChronoUnit.SECONDS);
    }

    private long calculateNewValue(long numOfDecrements) {
        long decrementedValue = value - (numOfDecrements * decreaseAmount);
        return Math.max(minValue, decrementedValue);
    }

    public static class Builder {
        private Integer startingValue;
        private Integer minValue;
        private Integer maxValue;
        private Duration timeToDecrease;
        private Integer decreaseAmount;

        public Builder minValue(int minValue) {
            this.minValue = minValue;
            return this;
        }

        public Builder maxValue(int maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        public Builder timeToDecrease(Duration timeToDecrease) {
            this.timeToDecrease = timeToDecrease;
            return this;
        }

        public Builder decreaseAmount(int decreaseAmount) {
            this.decreaseAmount = decreaseAmount;
            return this;
        }

        public Builder startingValue(int startingValue) {
            this.startingValue = startingValue;
            return this;
        }

        public Attribute build() {
            if (Objects.isNull(startingValue)) throw new IllegalArgumentException("Starting value is required");
            if (Objects.isNull(minValue)) throw new IllegalArgumentException("Min value is required");
            if (Objects.isNull(maxValue)) throw new IllegalArgumentException("Max value is required");
            if (Objects.isNull(timeToDecrease)) throw new IllegalArgumentException("Time to decrease is required");
            if (Objects.isNull(decreaseAmount)) throw new IllegalArgumentException("Decrease amount is required");
            return new Attribute(startingValue, minValue, maxValue, timeToDecrease, decreaseAmount);
        }
    }
}
