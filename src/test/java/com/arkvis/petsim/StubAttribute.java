package com.arkvis.petsim;

import java.time.Duration;

public class StubAttribute extends Attribute {

    @Override
    long getValue() {
        return 0;
    }

    @Override
    void progressTime(Duration time) {

    }
}
