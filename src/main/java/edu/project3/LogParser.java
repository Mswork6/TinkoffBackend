package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LogParser {
    public static List<LogData> parseLogData(String logPath) {
        try {
            if (logPath.startsWith("http")) {
                // Если путь начинается с "http", предполагаем, что это URL
                return parseLogFromURL(logPath);
            } else {
                // Иначе считаем, что это путь к файлу на диске
                return parseLogFromFile(logPath);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка при получении данных :" +e.getMessage());
            return List.of(); // Возвращаем пустой список в случае ошибки
        }
    }

    private static List<LogData> parseLogFromFile(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return reader.lines()
                .map(LogData::new)
                .collect(Collectors.toList());
        }
    }

    private static List<LogData> parseLogFromURL(String urlString) throws IOException, InterruptedException {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
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


