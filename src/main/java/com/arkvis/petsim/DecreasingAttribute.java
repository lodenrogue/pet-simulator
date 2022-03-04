package com.arkvis.petsim;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DecreasingAttribute implements Attribute {

    private final Duration timeToDecrease;
    private final int decreaseAmount;
    private final int minValue;

    private long value;
    private Duration timeSinceDecrease;

    DecreasingAttribute(int minValue, int maxValue, Duration timeToDecrease, int decreaseAmount) {
        this.minValue = minValue;
        this.timeToDecrease = timeToDecrease;
        this.decreaseAmount = decreaseAmount;

        value = maxValue;
        timeSinceDecrease = Duration.of(0, ChronoUnit.MINUTES);
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public void progressTime(Duration timePassed) {
        timeSinceDecrease = timeSinceDecrease.plus(timePassed);

        if (timeSinceDecrease.compareTo(timeToDecrease) >= 0) {
            long numOfDecrements = timeSinceDecrease.dividedBy(timeToDecrease);
            value = calculateNewValue(numOfDecrements);

            Duration totalIncrementTime = getTotalIncrementTime(numOfDecrements);
            timeSinceDecrease = timeSinceDecrease.minus(totalIncrementTime);
        }
    }

    private Duration getTotalIncrementTime(long numOfIncrements) {
        long totalIncrementInSeconds = timeToDecrease.getSeconds() * numOfIncrements;
        return Duration.of(totalIncrementInSeconds, ChronoUnit.SECONDS);
    }

    private long calculateNewValue(long numOfDecrements) {
        long decrementedValue = value - (numOfDecrements * decreaseAmount);
        return Math.max(minValue, decrementedValue);
    }

    public static class Builder {
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

        public Attribute build() {
            if (Objects.isNull(minValue)) throw new IllegalArgumentException("Min value is required");
            if (Objects.isNull(maxValue)) throw new IllegalArgumentException("Max value is required");
            if (Objects.isNull(timeToDecrease)) throw new IllegalArgumentException("Time to decrease is required");
            if (Objects.isNull(decreaseAmount)) throw new IllegalArgumentException("Decrease amount is required");
            return new DecreasingAttribute(minValue, maxValue, timeToDecrease, decreaseAmount);
        }
    }
}
