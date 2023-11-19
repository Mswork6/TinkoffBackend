package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskMapTest {
    @Test
    @DisplayName("Проверка добавления элементов")
    void testPutAndGet() {
        // given
        DiskMap diskMap = new DiskMap("src\\test\\resources\\test_diskmap.txt");

        // when
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // then
        assertEquals("value1", diskMap.get("key1"));
        assertEquals("value2", diskMap.get("key2"));
    }

    @Test
    @DisplayName("Проверка корректной длины")
    void testSize() {
        // given
        DiskMap diskMap = new DiskMap("src\\test\\resources\\test_diskmap1.txt");

        // when

        // then
        assertEquals(0, diskMap.size());

        diskMap.put("key1", "value1");
        assertEquals(1, diskMap.size());

        diskMap.put("key2", "value2");
        assertEquals(2, diskMap.size());

        diskMap.remove("key1");
        assertEquals(1, diskMap.size());
    }

    @Test
    @DisplayName("Проверка наличия ключей и значений")
    void testContainsKeyAndValue() {
        // given
        DiskMap diskMap = new DiskMap("src\\test\\resources\\test_diskmap2.txt");

        // when
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // then
        assertTrue(diskMap.containsKey("key1"));
        assertFalse(diskMap.containsKey("key3"));

        assertTrue(diskMap.containsValue("value2"));
        assertFalse(diskMap.containsValue("value3"));
    }

    @Test
    @DisplayName("Проверка корректного удаления элемента")
    void testRemove() {
        // given
        DiskMap diskMap = new DiskMap("src\\test\\resources\\test_diskmap3.txt");

        // when
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // then
        assertEquals("value1", diskMap.remove("key1"));
        assertNull(diskMap.get("key1"));
        assertEquals(1, diskMap.size());
    }

    @Test
    @DisplayName("Проверка удаления всех элементов")
    void testClear() {
        // given
        DiskMap diskMap = new DiskMap("src\\test\\resources\\test_diskmap4.txt");

        // when
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.clear();

        // then
        assertEquals(0, diskMap.size());
        assertNull(diskMap.get("key1"));
        assertNull(diskMap.get("key2"));
    }

    @Test
    @DisplayName("Проверка чтения элементов из файла")
    void testReadData() {
        // given
        DiskMap diskMap = new DiskMap("src\\test\\resources\\test_diskmap5.txt");

        // when

        // then
        assertEquals(3 ,diskMap.size());
        assertEquals("value1", diskMap.get("test1"));
        assertEquals("value2", diskMap.get("test2"));
        assertEquals("value3", diskMap.get("test3"));
    }
}
