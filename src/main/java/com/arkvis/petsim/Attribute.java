package com.arkvis.petsim;

import java.time.Duration;

public abstract class Attribute {

    abstract long getValue();

    abstract void progressTime(Duration time);

    abstract void giveBoon(int amount);
}
