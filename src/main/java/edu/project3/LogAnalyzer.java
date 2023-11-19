package edu.project3;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {
    private final List<LogData> logRecords;

    public LogAnalyzer(List<LogData> logRecords) {
        this.logRecords = logRecords;
    }

    public LogStats collectStats(String fileName) {
        return new LogStats(
            fileName,
            Long.toString(getTotalRequests()),
            getStartDate(),
            getEndDate(),
            getAverageResponseSize(),
            getTopResources(),
            getResponseCodes()
        );
    }
    private String getStartDate() {
        return logRecords.stream()
            .map(LogData::getTimestamp)
            .min(OffsetDateTime::compareTo)
            .map(timestamp -> timestamp.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
            .orElse("-");
    }

    private String getEndDate() {
        return logRecords.stream()
            .map(LogData::getTimestamp)
            .max(OffsetDateTime::compareTo)
            .map(timestamp -> timestamp.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
            .orElse("-");
    }

    private long getTotalRequests() {
        return logRecords.size();
    }

    private String getAverageResponseSize() {
        double averageSize = logRecords.stream()
            .mapToLong(LogData::getBodyBytesSent)
            .average()
            .orElse(0.0);
        return String.format("%.2f bytes", averageSize);
    }

    private Map<String, Long> getTopResources() {
        return logRecords.stream()
            .collect(Collectors.groupingBy(LogData::getRequest, Collectors.counting()))
            .entrySet().stream()
            .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
            .limit(3)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Integer, Long> getResponseCodes() {
        return logRecords.stream()
            .collect(Collectors.groupingBy(LogData::getStatus, Collectors.counting()));
    }
}

