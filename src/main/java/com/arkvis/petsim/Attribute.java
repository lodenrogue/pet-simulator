package com.arkvis.petsim;

import java.time.Duration;

interface Attribute {

    long getValue();

    void progressTime(Duration time);
}
