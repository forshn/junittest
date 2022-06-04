package ru.forsh.unittesting.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {

    private Car car;

    @BeforeEach
    void createCar() {
        car = new Car("Skoda", "a234bc", 2019, "Forsh");
    }

    @Test
    void getManufacturer() {
        assertEquals("Skoda", car.getManufacturer());
    }

    @Test
    void getNumber() {
        assertEquals("a234bc", car.getNumber());
    }

    @Test
    void setNumber() {
        car.setNumber("g202tt");
        assertEquals("g202tt", car.getNumber());
    }

    @Test
    void getYear() {
        assertEquals(2019, car.getYear());
    }

    @Test
    void getOwner() {
        assertEquals("Forsh", car.getOwner());
    }

    @Test
    void setOwner() {
        car.setOwner("Lera");
        assertEquals("Lera", car.getOwner());
    }

    @Test
    void getOwnerTest() {
        assertArrayEquals(new String[]{"Forsh"}, car.getOwners().toArray());
    }

    @Test
    void getOwnerIfOwnerChanges() {
        car.setOwner("Lera");
        assertArrayEquals(new String[]{"Forsh", "Lera"}, car.getOwners().toArray());
    }

    @Test
    void privateMethodTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = car.getClass().getDeclaredMethod("TestMethod", null);
        method.setAccessible(true);
        assertEquals("abc", method.invoke(car).toString());
    }
}