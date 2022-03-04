package com.arkvis.petsim;

import java.time.Duration;

public class Pet {
    private final String name;
    private final Attribute hunger;
    private final Attribute happiness;

    public Pet(String name, Attribute hunger, Attribute happiness) {
        this.name = name;
        this.hunger = hunger;
        this.happiness = happiness;
    }

    public String getName() {
        return name;
    }

    public long getHunger() {
        return hunger.getValue();
    }

    public void progressTime(Duration time) {
        hunger.progressTime(time);
        happiness.progressTime(time);
    }

    public long getHappiness() {
        return happiness.getValue();
    }
}
