package com.arkvis.petsim;

import java.time.Duration;

public class DefaultDecreasingAttribute extends Attribute {
    private final Attribute attribute;

    public DefaultDecreasingAttribute(Duration timeToDecrease) {
        attribute = new DecreasingAttribute.Builder()
                .minValue(0)
                .maxValue(100)
                .timeToDecrease(timeToDecrease)
                .decreaseAmount(1)
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
}
