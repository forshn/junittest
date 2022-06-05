package ru.forsh.unittesting.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {

    private Car car;

    //Запуск тестов параллельно, хорошо на больших данных. Кол-во потоков не больше кол-ва ядер компьютера
    @Execution(ExecutionMode.CONCURRENT)
    @BeforeEach
    void createCar() {
        car = new Car("Skoda", "a234bc", 2019, "Forsh");
    }

    @Execution(ExecutionMode.CONCURRENT)
    @Test
    void getManufacturer() {
        assertEquals("Skoda", car.getManufacturer());
    }

    @Execution(ExecutionMode.CONCURRENT)
    @Test
    void getNumber() {
        assertEquals("a234bc", car.getNumber());
    }

    @Execution(ExecutionMode.CONCURRENT)
    @Test
    void setNumber() {
        car.setNumber("g202tt");
        assertEquals("g202tt", car.getNumber());
    }

    // Параметры нужны для проверки входящий параметров в методе
    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest
    @ValueSource(strings = {"abc", "aaa"}) // Подаёт одновременно один параметр
    @NullSource
    @EmptySource
    void testSetNumberMultipleValues(String number) {
        car.setNumber(number);
        assertEquals(number, car.getNumber());
    }

    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest
    @CsvSource({"'123', '123'", // Когда разные входящие параметры, по парам
            "'aaa', 'aaa'"})
    void testSetNumberMultipleValuesWithCsvSource(String number, String x) {
        car.setNumber(number);
        assertEquals(x, car.getNumber());
    }

   /* @ParameterizedTest
    @CsvSource({"2", "6",
            "8", "12"})
    void testIntsCsv(int input, int output) {
        assertEquals(car.testInt(input), output);
    }
*/
   @Execution(ExecutionMode.CONCURRENT)
    @Test
    void getYear() {
        assertEquals(2019, car.getYear());
    }

    @Execution(ExecutionMode.CONCURRENT)
    @Test
    void getOwner() {
        assertEquals("Forsh", car.getOwner());
    }

    @Execution(ExecutionMode.CONCURRENT)
    @Test
    void setOwner() {
        car.setOwner("Lera");
        assertEquals("Lera", car.getOwner());
    }

    @Execution(ExecutionMode.CONCURRENT)
    @Test
    void getOwnerTest() {
        assertArrayEquals(new String[]{"Forsh"}, car.getOwners().toArray());
    }

    @Execution(ExecutionMode.CONCURRENT)
    @Test
    void getOwnerIfOwnerChanges() {
        car.setOwner("Lera");
        assertArrayEquals(new String[]{"Forsh", "Lera"}, car.getOwners().toArray());
    }

    @Execution(ExecutionMode.CONCURRENT)
    @Test
    void privateMethodTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = car.getClass().getDeclaredMethod("TestMethod", null);
        method.setAccessible(true);
        assertEquals("abc", method.invoke(car).toString());
    }

    // тестовые параметры из файла
    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest
    @DisplayName("Test demonstrates how test data could be called from file")
    @CsvFileSource(resources = "/test-data.csv", delimiter = '|', numLinesToSkip = 1)
    void testNumbers(String input, String expected){
        car.setNumber(input);
        assertEquals(expected, car.getNumber());
    }
}