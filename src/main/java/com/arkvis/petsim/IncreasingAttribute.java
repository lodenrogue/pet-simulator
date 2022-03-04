package com.arkvis.petsim;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class IncreasingAttribute implements Attribute {
    private final Integer maxValue;
    private final Duration timeToIncrease;
    private final Integer increaseAmount;

    private Duration timeSinceIncrease;
    private long value;

    IncreasingAttribute(int minAmount, int maxAmount, Duration timeToIncrease, int increaseAmount) {
        this.maxValue = maxAmount;
        this.timeToIncrease = timeToIncrease;
        this.increaseAmount = increaseAmount;

        timeSinceIncrease = Duration.of(0, ChronoUnit.MINUTES);
        this.value = minAmount;
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public void progressTime(Duration timePassed) {
        timeSinceIncrease = timeSinceIncrease.plus(timePassed);

        if (timeSinceIncrease.compareTo(timeToIncrease) >= 0) {
            long numOfIncrements = timeSinceIncrease.dividedBy(timeToIncrease);
            value = calculateNewValue(numOfIncrements);

            Duration totalIncrementTime = getTotalIncrementTime(numOfIncrements);
            timeSinceIncrease = timeSinceIncrease.minus(totalIncrementTime);
        }
    }

    private Duration getTotalIncrementTime(long numOfIncrements) {
        long totalIncrementInSeconds = timeToIncrease.getSeconds() * numOfIncrements;
        return Duration.of(totalIncrementInSeconds, ChronoUnit.SECONDS);
    }

    private long calculateNewValue(long numOfIncrements) {
        long incrementedValue = value + (numOfIncrements * increaseAmount);
        return Math.min(maxValue, incrementedValue);
    }

    public static class Builder {
        private Integer minValue;
        private Integer maxValue;
        private Duration timeToIncrease;
        private Integer increaseAmount;


        public Builder minValue(int minValue) {
            this.minValue = minValue;
            return this;
        }

        public Builder maxValue(int maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        public Builder timeToIncrease(Duration timeToIncrease) {
            this.timeToIncrease = timeToIncrease;
            return this;
        }

        public Builder increaseAmount(int increaseAmount) {
            this.increaseAmount = increaseAmount;
            return this;
        }

        public Attribute build() {
            if (Objects.isNull(minValue)) throw new IllegalArgumentException("Min value is required");
            if (Objects.isNull(maxValue)) throw new IllegalArgumentException("Max value is required");
            if (Objects.isNull(timeToIncrease)) throw new IllegalArgumentException("Time to increase is required");
            if (Objects.isNull(increaseAmount)) throw new IllegalArgumentException("Increase amount is required");
            return new IncreasingAttribute(minValue, maxValue, timeToIncrease, increaseAmount);
        }
    }
}
