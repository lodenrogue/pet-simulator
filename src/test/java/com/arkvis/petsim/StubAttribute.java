package com.arkvis.petsim;

import java.time.Duration;

public class StubAttribute implements Attribute {

    @Override
    public long getValue() {
        return 0;
    }

    @Override
    public void progressTime(Duration time) {

    }
}
