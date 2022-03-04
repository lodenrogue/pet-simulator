package com.arkvis.petsim;

import java.time.Duration;

public class Pet {
    private final String name;
    private final Hunger hunger;

    public Pet(String name, Hunger hunger) {
        this.name = name;
        this.hunger = hunger;
    }

    public String getName() {
        return name;
    }

    public long getHunger() {
        return hunger.getValue();
    }

    public void progressTime(Duration time) {
        hunger.progressTime(time);
    }
}
