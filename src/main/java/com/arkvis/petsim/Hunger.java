package com.arkvis.petsim;

import java.util.Objects;

public class Hunger {

    private final Integer minValue;
    private final Integer maxValue;
    private final Time timeToIncrement;
    private final Integer incrementAmount;
    private int value;

    Hunger(int minAmount, int maxAmount, Time timeToIncrement, int incrementAmount) {
        this.minValue = minAmount;
        this.maxValue = maxAmount;
        this.timeToIncrement = timeToIncrement;
        this.incrementAmount = incrementAmount;
        this.value = minAmount;
    }

    public int getValue() {
        return value;
    }

    public void progressTime(Time time) {
        if (time.toSeconds() >= timeToIncrement.toSeconds()) {
            value += Math.min(maxValue, value + incrementAmount);
        }
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
