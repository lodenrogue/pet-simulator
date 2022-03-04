package com.arkvis.petsim;

import java.time.Duration;
import java.util.Objects;

public class DecreasingAttribute implements Attribute {

    private final int value;

    DecreasingAttribute(int minValue, int maxValue, Duration timeToDecrease, int decreaseAmount) {
        this.value = maxValue;
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public void progressTime(Duration time) {

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
