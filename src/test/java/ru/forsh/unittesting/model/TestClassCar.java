package ru.forsh.unittesting.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClassCar {

    //Проверка работы тестов под разные ОС и версии JAVA
    @Test
    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
    @EnabledOnJre({JRE.JAVA_17, JRE.JAVA_8})
    void getCarYear(){
        Car car = new Car("Skoda", "a222aa", 2015, "Me");
        assertEquals(2015, car.getYear());
    }
}
