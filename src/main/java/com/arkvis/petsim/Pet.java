package com.arkvis.petsim;

import java.time.Duration;

public class Pet {
    private final String name;
    private final Attribute hunger;
    private final Attribute happiness;
    private final Attribute hygiene;
    private final Attribute bladder;
    private final Attribute energy;

    public Pet(String name,
               Attribute hunger,
               Attribute happiness,
               Attribute hygiene,
               Attribute bladder,
               Attribute energy) {

        this.name = name;
        this.hunger = hunger;
        this.happiness = happiness;
        this.hygiene = hygiene;
        this.bladder = bladder;
        this.energy = energy;
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
        bladder.progressTime(time);
        energy.progressTime(time);
    }

    public long getHappiness() {
        return happiness.getValue();
    }

    public long getHygiene() {
        return hygiene.getValue();
    }

    public long getBladder() {
        return bladder.getValue();
    }

    public long getEnergy() {
        return energy.getValue();
    }

    public void feed(int feedAmount) {
        hunger.giveBoon(feedAmount);
    }
}
