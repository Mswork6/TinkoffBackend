package edu.project3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class LogReport {
    private static final String[] TITLE_NAMES = {"Метрика", "Значение", "Ресурс", "Количество",
        "Имя", "Код"};

    private LogStats stats;

    public LogReport(LogStats stats) {
        this.stats = stats;
    }

    private int countSizeOfColumn(List<String> list, String header) {
        int size1 = list.stream()
            .map(String::length)
            .max(Integer::compare)
            .orElse(0);
        return Math.max(size1, header.length());

    }

    private int countSizeOfColumn(Map<String, Long> map, String header) {
        List<String> list = map.keySet().stream().toList();

        return countSizeOfColumn(list, header);
    }

    private String makeFirstTable() {
        List<String> dataList = new ArrayList<>(List.of(new String[] {
            stats.fileName(),
            stats.startDate(),
            stats.endDate(),
            stats.totalRequests(),
            stats.averageResponseSize()
        }));
        int column2Size = countSizeOfColumn(dataList, TITLE_NAMES[1]);

        StringBuilder table = new StringBuilder();
        table.append("|        Метрика        |")
            .append(" ".repeat(column2Size - TITLE_NAMES[1].length() + 2))
            .append(TITLE_NAMES[1]).append("|\n");
        table.append("|:---------------------:|")
                .append("-".repeat(column2Size + 1)).append(":|\n");
        table.append("|       Файл(-ы)        |")
            .append(" ".repeat(column2Size - stats.fileName().length() + 2))
            .append(stats.fileName()).append("|\n");
        table.append("|    Начальная дата     |")
            .append(" ".repeat(column2Size - stats.startDate().length() + 2))
            .append(stats.startDate()).append("|\n");
        table.append("|     Конечная дата     |")
            .append(" ".repeat(column2Size - stats.endDate().length() + 2))
            .append(stats.endDate()).append("|\n");
        table.append("|  Количество запросов  |")
            .append(" ".repeat(column2Size - stats.totalRequests().length() + 2))
            .append(stats.totalRequests()).append("|\n");
        table.append("| Средний размер ответа |")
            .append(" ".repeat(column2Size - stats.averageResponseSize().length() + 2))
            .append(stats.averageResponseSize()).append("|\n");

        return table.toString();
    }

    private String makeSecondTable() {
        int column1size = countSizeOfColumn(stats.topResources(), TITLE_NAMES[2]);
        List<String> valuesList = Collections.singletonList(
            stats.topResources().values().stream().toList().toString());
        int column2size = countSizeOfColumn(valuesList, TITLE_NAMES[3]);
        StringBuilder table = new StringBuilder();

        table.append("|").append(TITLE_NAMES[2])
            .append(" ".repeat(column1size - TITLE_NAMES[2].length() + 2))
            .append("|").append(" ".repeat(column2size - TITLE_NAMES[3].length() + 2))
            .append(TITLE_NAMES[3]).append("|\n");

        table.append("|:").append("-".repeat(column1size)).append(":|")
            .append("-".repeat(column2size + 1)).append(":|\n");

        for (Map.Entry<String, Long> entry : stats.topResources().entrySet()) {
            table.append("|").append(entry.getKey())
                .append(" ".repeat(column1size - entry.getKey().length() + 2))
                .append("|").append(" ".repeat(column2size - entry.getValue().toString().length() + 2))
                .append(entry.getValue().toString()).append("|\n");
        }
        return table.toString();
    }

    private String makeThirdTable() {
        List<String> keysList = Collections.singletonList(stats.responseCodes()
            .keySet().stream().toList().toString());
        int column1size = countSizeOfColumn(keysList, TITLE_NAMES[5]);
        List<String> valuesList = Collections.singletonList(
            stats.responseCodes().values().stream().toList().toString());
        int column2size = countSizeOfColumn(valuesList, TITLE_NAMES[3]);
        StringBuilder table = new StringBuilder();

        table.append("|").append(TITLE_NAMES[5])
            .append(" ".repeat(column1size - TITLE_NAMES[5].length() + 2))
            .append("|").append(" ".repeat(column2size - TITLE_NAMES[3].length() + 2))
            .append(TITLE_NAMES[3]).append("|\n");

        table.append("|:").append("-".repeat(column1size)).append(":|")
            .append("-".repeat(column2size + 1)).append(":|\n");

        for (Map.Entry<Integer, Long> entry : stats.responseCodes().entrySet()) {
            table.append("|").append(entry.getKey())
                .append(" ".repeat(column1size - entry.getKey().toString().length() + 2))
                .append("|").append(" ".repeat(column2size - entry.getValue().toString().length() + 2))
                .append(entry.getValue().toString()).append("|\n");
        }
        return table.toString();
    }

    private String generateReportMd() {
        StringBuilder report = new StringBuilder();
        report.append("#### Общая информация\n\n");
        report.append(makeFirstTable());
        report.append("\n");
        report.append("#### Запрашиваемые ресурсы\n\n");
        report.append(makeSecondTable());
        report.append("\n");
        report.append("#### Коды ответа\n\n");
        report.append(makeThirdTable());
        report.append("\n");

        return report.toString();
    }

    public void createMarkdownFile() {
        Path filePath = Paths.get("logs.md");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            String report = generateReportMd();
            writer.write(report);
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

}
