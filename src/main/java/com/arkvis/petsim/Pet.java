package com.arkvis.petsim;

import java.time.Duration;

public class Pet {
    private final String name;
    private final Attribute hunger;
    private final Attribute happiness;
    private final Attribute hygiene;

    public Pet(String name, Attribute hunger, Attribute happiness, Attribute hygiene) {
        this.name = name;
        this.hunger = hunger;
        this.happiness = happiness;
        this.hygiene = hygiene;
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
        hygiene.progressTime(time);
    }

    public long getHappiness() {
        return happiness.getValue();
    }

    public long getHygiene() {
        return hygiene.getValue();
    }
}
