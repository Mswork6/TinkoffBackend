package edu.hw9;

import edu.hw9.Task2.CountFilesTask;
import edu.hw9.Task2.SearchFilesTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    @DisplayName("Проверка работоспособности метода поиска директорий")
    public void testCountFilesTask() {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {

            // Создаем временную директорию для теста
            File tempDir = new File(System.getProperty("java.io.tmpdir") + File.separator + "testDir");
            tempDir.mkdirs();

            try {
                // Создаем несколько файлов в директории
                for (int i = 0; i < 100; i++) {
                    File file = new File(tempDir, "file" + i + ".txt");
                    file.createNewFile();
                }

                // Выполняем задачу по подсчету файлов
                CountFilesTask countFilesTask = new CountFilesTask(tempDir, 50);
                int result = forkJoinPool.invoke(countFilesTask);

                // Проверяем результат
                assertEquals(1, result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Удаляем временную директорию
                deleteDirectory(tempDir);
            }
        }
    }

    @Test
    @DisplayName("Проверка работоспособности метода поиска файлов по предикату")
    public void testSearchFilesTask() {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {

            // Создаем временную директорию для теста
            File tempDir = new File(System.getProperty("java.io.tmpdir") + File.separator + "testDir");
            tempDir.mkdirs();

            try {
                // Создаем несколько файлов в директории
                for (int i = 0; i < 10; i++) {
                    File file = new File(tempDir, "file" + i + ".txt");
                    file.createNewFile();
                }

                // Выполняем задачу по поиску файлов
                SearchFilesTask searchFilesTask =
                    new SearchFilesTask(tempDir, file -> file.getName().startsWith("file"));
                List<File> result = forkJoinPool.invoke(searchFilesTask);

                // Проверяем результат
                assertEquals(10, result.size()); // Ожидаем, что найдется 10 файлов
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Удаляем временную директорию
                deleteDirectory(tempDir);
            }
        }
    }

    private void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
}
