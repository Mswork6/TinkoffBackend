package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @Test
    @DisplayName("Проверка корректной работы программы")
    void testCloneFileNoExistingCopies() {
        // Given
        Path sourcePath = Paths.get("src\\test\\resources\\test.txt");

        // When
        Task2.cloneFile(sourcePath);
        Path clonedPath = Paths.get("src\\test\\resources\\test — копия.txt");

        // Then
        assertTrue(Files.exists(clonedPath));
        assertTrue(Files.isRegularFile(clonedPath));
    }

}
