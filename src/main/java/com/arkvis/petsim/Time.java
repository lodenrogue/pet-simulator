package com.arkvis.petsim;

import java.util.concurrent.TimeUnit;

public class Time {
    private final long duration;
    private final TimeUnit timeUnit;

    public Time(long duration, TimeUnit timeUnit) {
        this.duration = duration;
        this.timeUnit = timeUnit;
    }

    public long toSeconds() {
        return timeUnit.toSeconds(duration);
    }

    public Time plus(Time addend) {
        long total = timeUnit.toSeconds(duration) + addend.toSeconds();
        return new Time(total, TimeUnit.SECONDS);
    }

    public Time minus(Time subtrahend) {
        long total = timeUnit.toSeconds(duration) - subtrahend.toSeconds();
        return new Time(total, TimeUnit.SECONDS);
    }

    public long dividedBy(Time divisor) {
        return timeUnit.toSeconds(duration) / divisor.toSeconds();
    }

    public boolean isGreaterOrEqualTo(Time other) {
        return timeUnit.toSeconds(duration) >= other.toSeconds();
    }
}
