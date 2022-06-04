package ru.forsh.unittesting.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    static Dog dog;

    @BeforeAll
    static void prepareData() {
        dog = new Dog("albert", 2);
    }

    @Test
    void getNameTest() {
        assertEquals("albert", dog.getName());
    }

    @Test
    void setNameTest() {
        dog.setName("maria");
        assertEquals("albert", dog.getName());
    }

    @Test
    void setNameTestIfEmpty() {
        Dog dog = new Dog("", 2);
        dog.setName("maria");
        assertEquals("maria", dog.getName());
    }

    @Test
    void getAgeTest() {
    }

    @Test
    void setAgeTest() {
    }
}