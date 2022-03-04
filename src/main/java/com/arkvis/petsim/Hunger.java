package com.arkvis.petsim;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Hunger {

    private final Integer maxValue;
    private final Duration timeToIncrement;
    private final Integer incrementAmount;

    private Duration timeSinceIncrement;
    private long value;

    Hunger(int minAmount, int maxAmount, Duration timeToIncrement, int incrementAmount) {
        this.maxValue = maxAmount;
        this.timeToIncrement = timeToIncrement;
        this.incrementAmount = incrementAmount;

        timeSinceIncrement = Duration.of(0, ChronoUnit.MINUTES);
        this.value = minAmount;
    }

    public long getValue() {
        return value;
    }

    public void progressTime(Duration timePassed) {
        timeSinceIncrement = timeSinceIncrement.plus(timePassed);

        if (timeSinceIncrement.compareTo(timeToIncrement) >= 0) {
            long numOfIncrements = timeSinceIncrement.dividedBy(timeToIncrement);
            value = calculateNewValue(numOfIncrements);

            Duration totalIncrementTime = getTotalIncrementTime(numOfIncrements);
            timeSinceIncrement = timeSinceIncrement.minus(totalIncrementTime);
        }
    }

    private Duration getTotalIncrementTime(long numOfIncrements) {
        long totalIncrementInSeconds = timeToIncrement.getSeconds() * numOfIncrements;
        return Duration.of(totalIncrementInSeconds, ChronoUnit.SECONDS);
    }

    private long calculateNewValue(long numOfIncrements) {
        long incrementedValue = value + (numOfIncrements * incrementAmount);
        return Math.min(maxValue, incrementedValue);
    }

    public static class Builder {
        private Integer minValue;
        private Integer maxValue;
        private Duration timeToIncrement;
        private Integer incrementAmount;


        public Builder minValue(int minValue) {
            this.minValue = minValue;
            return this;
        }

        public Builder maxValue(int maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        public Builder timeToIncrement(Duration time) {
            this.timeToIncrement = time;
            return this;
        }

        public Builder incrementAmount(int amount) {
            this.incrementAmount = amount;
            return this;
        }

        public Hunger build() {
            if (Objects.isNull(minValue)) throw new IllegalArgumentException("Min value is required");
            if (Objects.isNull(maxValue)) throw new IllegalArgumentException("Max value is required");
            if (Objects.isNull(timeToIncrement)) throw new IllegalArgumentException("Time to increment is required");
            if (Objects.isNull(incrementAmount)) throw new IllegalArgumentException("Increment amount is required");
            return new Hunger(minValue, maxValue, timeToIncrement, incrementAmount);
        }
    }
}
