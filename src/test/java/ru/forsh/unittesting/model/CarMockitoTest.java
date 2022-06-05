package ru.forsh.unittesting.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CarMockitoTest {

    @Test
    void getManufacturerTest(){
        Car car = Mockito.mock(Car.class);
        assertEquals(null, car.getManufacturer());
    }

    @Test
    void remoteServiceReturnValue(){
        Car car = Mockito.mock(Car.class);
        when(car.testInt(4)).thenReturn(4);
        assertEquals(4, car.testInt(4));
    }

    @Test
    void getOwner(){
        Car car = Mockito.mock(Car.class);
        when(car.getOwner()).thenReturn("Kolay");
        assertEquals("Kolay", car.getOwner());
    }
}
