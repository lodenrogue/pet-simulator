package com.arkvis.petsim;

import java.time.Duration;

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
        private int minValue;
        private int maxValue;
        private Duration timeToDecrease;
        private int decreaseAmount;

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
            return new DecreasingAttribute(minValue, maxValue, timeToDecrease, decreaseAmount);
        }
    }
}
