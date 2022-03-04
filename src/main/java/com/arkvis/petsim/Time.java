package com.arkvis.petsim;

import java.util.concurrent.TimeUnit;

public class Time {
    private final int duration;
    private final TimeUnit timeUnit;

    public Time(int duration, TimeUnit timeUnit) {
        this.duration = duration;
        this.timeUnit = timeUnit;
    }

    public long toSeconds() {
        return timeUnit.toSeconds(duration);
    }
}
