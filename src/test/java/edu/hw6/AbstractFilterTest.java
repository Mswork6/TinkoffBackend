package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractFilterTest {
    @Test
    @DisplayName("Проверка фильтрации png файлов")
    void checkPngFilter() {
        // given
        Path dir = Paths.get("src/test/resources/");
        List<Path> filteredPaths = new ArrayList<>();

        AbstractFilter filter = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.WRITABLE)
            .and(AbstractFilter.magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
            .and(AbstractFilter.largerThan(100_000))
            .and(AbstractFilter.globMatches("*.png"))
            .and(AbstractFilter.regexContains("[-image]"));

        // when
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(filteredPaths::add);
        } catch (IOException e) {
            System.err.println("Ошибка во время фильтрации: " + e.getMessage());
        }

        // then
        assertEquals(filteredPaths.size(), 4);
        assertEquals(filteredPaths.get(0).toString(), "src\\test\\resources\\Cyberpunk-image.png");
        assertEquals(filteredPaths.get(1).toString(), "src\\test\\resources\\Cyberpunk-image1.png");
        assertEquals(filteredPaths.get(2).toString(), "src\\test\\resources\\Cyberpunk-image2.png");
        assertEquals(filteredPaths.get(3).toString(), "src\\test\\resources\\Cyberpunk-image3.png");
    }

    @Test
    @DisplayName("Проверка фильтрации png файлов")
    void checkPngNonExistFilter() {
        // given
        Path dir = Paths.get("src/test/resources/");
        List<Path> filteredPaths = new ArrayList<>();

        AbstractFilter filter = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.WRITABLE)
            .and(AbstractFilter.magicNumber((byte) 0x89, (byte) 'L', (byte) 'M', (byte) 'B'))
            .and(AbstractFilter.largerThan(10_000))
            .and(AbstractFilter.globMatches("*.png"))
            .and(AbstractFilter.regexContains("NonExistingFiles"));

        // when
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(filteredPaths::add);
        } catch (IOException e) {
            System.err.println("Ошибка во время фильтрации: " + e.getMessage());
        }

        // then
        assertEquals(filteredPaths.size(), 0);
    }

    @Test
    @DisplayName("Проверка фильтрации txt файлов")
    void checkTxtFilter() {
        // given
        Path dir = Paths.get("src/test/resources/");
        List<Path> filteredPaths = new ArrayList<>();

        AbstractFilter filter = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.READABLE)
            .and(AbstractFilter.largerThan(-1))
            .and(AbstractFilter.globMatches("*.txt"))
            .and(AbstractFilter.regexContains("test_diskmap"));

        // when
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            entries.forEach(filteredPaths::add);
        } catch (IOException e) {
            System.err.println("Ошибка во время фильтрации: " + e.getMessage());
        }

        // then
        assertEquals(filteredPaths.size(), 6);
        assertEquals(filteredPaths.get(0).toString(), "src\\test\\resources\\test_diskmap.txt");
        assertEquals(filteredPaths.get(1).toString(), "src\\test\\resources\\test_diskmap1.txt");
        assertEquals(filteredPaths.get(2).toString(), "src\\test\\resources\\test_diskmap2.txt");
        assertEquals(filteredPaths.get(3).toString(), "src\\test\\resources\\test_diskmap3.txt");
        assertEquals(filteredPaths.get(4).toString(), "src\\test\\resources\\test_diskmap4.txt");
        assertEquals(filteredPaths.get(5).toString(), "src\\test\\resources\\test_diskmap5.txt");

    }
}
