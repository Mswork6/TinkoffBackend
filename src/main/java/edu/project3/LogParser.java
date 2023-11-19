package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser {
    public static List<LogData> parseLogData(String logPath) {
        try {
            if (logPath.startsWith("http")) {
                // Если путь начинается с "http", предполагаем, что это URL
                return parseLogFromURL(logPath);
            } else {
                if (logPath.contains("*")){
                    String path = PathParcer.parsePath(logPath);
                    return parseLogsFromDirectory(path);
                }
                // Иначе считаем, что это путь к файлу(ам) на диске
                return parseLogsFromDirectory(logPath);
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println("Ошибка при получении данных: " + e.getMessage());
            return List.of(); // Возвращаем пустой список в случае ошибки
        }
    }

    public static List<LogData> parseLogsFromDirectory(String directoryPath) {
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            return paths
                .filter(Files::isRegularFile)
                .flatMap(LogParser::tryParseLogFromFile)
                .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файлов: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private static Stream<LogData> tryParseLogFromFile(Path path) {
        try {
            return LogParser.parseLogFromFile(path)
                .stream()
                .onClose(() -> System.out.println("Файл " + path + " успешно обработан."));
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка при обработке файла " + path + ": " + e.getMessage());
            return Stream.empty();
        }
    }

    private static List<LogData> parseLogFromFile(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            return reader.lines()
                .map(LogData::new)
                .collect(Collectors.toList());
        } catch (IOException |UncheckedIOException e) {
            System.err.println("Ошибка при чтении файла " + filePath + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private static List<LogData> parseLogFromURL(String urlString) throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI(urlString);
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()))) {
                return reader.lines()
                    .map(LogData::new)
                    .collect(Collectors.toList());
            }
        }
    }
}
