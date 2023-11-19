package edu.project3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class PathParcer {
    public static String parsePath(String logPath) throws IOException {
        String[] parts = logPath.split("\\\\");
        String regex = "\\*{2}";
        StringBuilder startString = new StringBuilder();
        StringBuilder targetPath = new StringBuilder();
        int index = 0;
        while (index < parts.length) {
            if (!parts[index].contains("*")) {
                startString.append(parts[index]).append("\\\\");
            } else {
                break;
            }
            index += 1;
        }

        for (int i = index; i < parts.length; i++){
            if (parts[i].matches(regex)) {
                continue;
            }
            targetPath.append(parts[i]).append("\\\\");
        }
        startString.delete(startString.length() - 2, startString.length());
        targetPath.delete(targetPath.length() - 2, targetPath.length());
        Path start = Paths.get(startString.toString());
        if (logPath.contains("**")) {
            return findFileOrFolder(start, targetPath.toString());
        }
        if (logPath.contains("*")) {
            return findFileByPattern(start, targetPath.toString());
        }
        return null;
    }

    public static String findFileOrFolder(Path start, String targetName) throws IOException {
        final Path[] resultPath = {null}; // Используем массив для хранения найденного пути

        Files.walkFileTree(start, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.getFileName().toString().equals(targetName)) {
                    resultPath[0] = file.toAbsolutePath();
                    return FileVisitResult.TERMINATE; // прекращаем поиск после нахождения файла
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (dir.getFileName().toString().equals(targetName)) {
                    resultPath[0] = dir.toAbsolutePath();
                    return FileVisitResult.SKIP_SUBTREE; // пропускаем все поддиректории этой папки
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });

        return resultPath[0].toString();
    }

    public static String findFileByPattern(Path start, String partialPath) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(start, partialPath)) {
            for (Path path : stream) {
                if (Files.isRegularFile(path)) {
                    return path.toAbsolutePath().toString();
                }
            }
        } catch (Exception e) {
            // Обработка ошибок, например, если директория не существует
            System.err.println("Ошибка: " + e.getMessage());
        }
        return null;
    }

}
