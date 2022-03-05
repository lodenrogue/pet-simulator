package com.arkvis.petsim;

import java.time.Duration;

public class StubAttribute extends Attribute {

    StubAttribute() {
        super(0, 0, 0, Duration.ZERO, 0);
    }

    @Override
    long getValue() {
        return 0;
    }

    @Override
    void progressTime(Duration time) {
    }

    @Override
    void increase(int amount) {
    }
}
