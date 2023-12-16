package edu.hw10;

import edu.hw10.Task1.MyClass;
import edu.hw10.Task1.MyRecord;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Task1Test {

    private final RandomObjectGenerator generator = new RandomObjectGenerator();

    @Test
    @DisplayName("Проверка генерации класса")
    void testCreateRandomObjectForClass() {
        // given

        //when

        //then
        try {
            MyClass obj = (MyClass) generator.createRandomObject(MyClass.class);
            assertNotNull(obj);
            assertTrue(obj.intValue >= Integer.MIN_VALUE && obj.intValue <= Integer.MAX_VALUE);
            assertNotNull(obj.stringValue);
            assertTrue(obj.stringValue.length() >= 2 && obj.stringValue.length() <= 15);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Проверка генерации рекорда")
    //given

    //when

    //then
    void testCreateRandomObjectForRecord() {
        try {
            MyRecord record = (MyRecord) generator.createRandomObject(MyRecord.class);
            assertNotNull(record);
            assertTrue(record.val() >= 2 && record.val() <= 3);
            assertNotNull(record.val());
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Проверка генерации класса фарбричным методом")
    void testCreateRandomObjectWithFactoryMethod() {
        //given

        //when

        //then
        try {
            MyClass obj = (MyClass) generator.createRandomObject(MyClass.class, "create");
            assertNotNull(obj);
            assertTrue(obj.intValue >= Integer.MIN_VALUE && obj.intValue <= Integer.MAX_VALUE);
            assertNotNull(obj.stringValue);
            assertTrue(obj.stringValue.length() >= 2 && obj.stringValue.length() <= 15);
        } catch (Exception e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }
}
