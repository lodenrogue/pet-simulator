package com.arkvis.petsim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PetSimTest {
    private String name;

    @BeforeEach
    void setUp() {
        name = "TEST_NAME";
    }

    @Test
    void should_returnCorrectPetName_when_gettingName() {
        Pet pet = new Pet(name, new StubAttribute(), new StubAttribute());
        assertEquals(name, pet.getName());
    }
}
