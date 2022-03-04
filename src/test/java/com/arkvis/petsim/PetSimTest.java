package com.arkvis.petsim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetSimTest {

    private String name;
    private Hunger hunger;

    @BeforeEach
    void setUp() {
        name = "TEST_NAME";
        hunger = createHunger(0);
    }

    @Test
    void should_returnCorrectPetName_when_gettingName() {
        Pet pet = new Pet(name, hunger);
        assertEquals(name, pet.getName());
    }

    @Test
    public void should_returnCorrectStartingHunger_when_creatingPet() {
        int minValue = 0;
        Hunger hunger = createHunger(minValue);

        Pet pet = new Pet(name, hunger);
        assertEquals(minValue, pet.getHunger());
    }

    @Test
    public void should_returnIncreasedHunger_when_oneTimeUnitHasPassed() {
        int incrementAmount = 1;
        Time time = new Time(1, TimeUnit.MINUTES);

        Hunger hunger = new Hunger.Builder()
                .minValue(0)
                .maxValue(100)
                .timeToIncrement(new Time(1, TimeUnit.SECONDS))
                .incrementAmount(1)
                .build();

        Pet pet = new Pet(name, hunger);
        pet.progressTime(time);
        assertEquals(incrementAmount, pet.getHunger());
    }

    private Hunger createHunger(int minValue) {
        return new Hunger.Builder()
                .minValue(minValue)
                .maxValue(100)
                .timeToIncrement(new Time(1, TimeUnit.SECONDS))
                .incrementAmount(1)
                .build();
    }
}
