package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Task2 {
    public static void cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));

        int copyNumber = 1;
        Path targetPath;

        do {
            String copySuffix = (copyNumber == 1) ? " — копия" : " — копия (" + copyNumber + ")";
            String newFileName = baseName + copySuffix + extension;
            targetPath = path.resolveSibling(newFileName);
            copyNumber++;
        } while (Files.exists(targetPath));

        try {
            Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Ошибка при клонировании: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Пример использования функции
        Path filePath = Paths.get("D:\\answer.txt");
        cloneFile(filePath);
    }
}
