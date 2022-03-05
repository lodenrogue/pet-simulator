package com.arkvis.petsim;

import java.time.Duration;

public class SimpleIncreasingAttribute extends Attribute {
    private final Attribute attribute;

    public SimpleIncreasingAttribute(Duration timeToIncrease) {
        attribute = new IncreasingAttribute.Builder()
                .startingValue(0)
                .minValue(0)
                .maxValue(100)
                .timeToIncrease(timeToIncrease)
                .increaseAmount(1)
                .build();
    }

    @Override
    long getValue() {
        return attribute.getValue();
    }

    @Override
    void progressTime(Duration time) {
        attribute.progressTime(time);
    }

    @Override
    void giveBoon(int amount) {
        attribute.giveBoon(amount);
    }
}
