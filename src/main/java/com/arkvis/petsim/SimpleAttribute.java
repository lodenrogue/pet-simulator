package com.arkvis.petsim;

import java.time.Duration;

public class SimpleAttribute extends Attribute {

    public SimpleAttribute(Duration timeToDecrease) {
        super(100, 0, 100, timeToDecrease, 1);
    }
}
