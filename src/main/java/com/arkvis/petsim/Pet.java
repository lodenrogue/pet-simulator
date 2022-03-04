package com.arkvis.petsim;

public class Pet {
    private static final int MAX_HUNGER = 100;

    private final String name;
    private final Hunger hunger;

    public Pet(String name, Hunger hunger) {
        this.name = name;
        this.hunger = hunger;
    }

    public String getName() {
        return name;
    }

    public int getHunger() {
        return hunger.getValue();
    }

    public void progressTime(Time time) {
        hunger.progressTime(time);
    }
}
