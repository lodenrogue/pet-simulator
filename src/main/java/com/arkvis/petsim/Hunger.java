package com.arkvis.petsim;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Hunger {

    private final Integer maxValue;
    private final Time timeToIncrement;
    private final Integer incrementAmount;

    private Time timeSinceIncrement;
    private long value;

    Hunger(int minAmount, int maxAmount, Time timeToIncrement, int incrementAmount) {
        this.maxValue = maxAmount;
        this.timeToIncrement = timeToIncrement;
        this.incrementAmount = incrementAmount;

        timeSinceIncrement = new Time(0, TimeUnit.MINUTES);
        this.value = minAmount;
    }

    public long getValue() {
        return value;
    }

    public void progressTime(Time timePassed) {
        timeSinceIncrement = timeSinceIncrement.plus(timePassed);

        if (timeSinceIncrement.isGreaterOrEqualTo(timeToIncrement)) {
            long numOfIncrements = timeSinceIncrement.dividedBy(timeToIncrement);
            value = calculateNewValue(numOfIncrements);

            Time totalIncrementTime = getTotalIncrementTime(numOfIncrements);
            timeSinceIncrement = timeSinceIncrement.minus(totalIncrementTime);
        }
    }

    private Time getTotalIncrementTime(long numOfIncrements) {
        long totalIncrementInSeconds = timeToIncrement.toSeconds() * numOfIncrements;
        return new Time(totalIncrementInSeconds, TimeUnit.SECONDS);
    }

    private long calculateNewValue(long numOfIncrements) {
        long incrementedValue = value + (numOfIncrements * incrementAmount);
        return Math.min(maxValue, incrementedValue);
    }

    public static class Builder {
        private Integer minValue;
        private Integer maxValue;
        private Time timeToIncrement;
        private Integer incrementAmount;


        public Builder minValue(int minValue) {
            this.minValue = minValue;
            return this;
        }

        public Builder maxValue(int maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        public Builder timeToIncrement(Time time) {
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
