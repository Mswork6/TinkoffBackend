package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task4 {
    public static void writeToFile(String path) {
        Path filePath = Paths.get(path);

        // Создаем цепочку OutputStream'ов
        try (
            OutputStream fileOutputStream = Files.newOutputStream(filePath);
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                bufferedOutputStream,
                StandardCharsets.UTF_8
            );
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)
        ) {
            // Записываем текст в файл
            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            System.err.println("Ошибка при записи" + e.getMessage());
        }
    }

}
