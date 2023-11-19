package edu.project3;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final List<LogData> logRecords;

    public LogFilter(List<LogData> logRecords) {
        this.logRecords = logRecords;
    }

    public List<LogData> dateFilter(String fromDate, String toDate) {
        if (fromDate != null && toDate != null) {
            return filterByDateRange(fromDate, toDate);
        } else if (fromDate != null) {
            return filterOnlyFromDate(fromDate);
        } else if (toDate != null) {
            return filterOnlyToDate(toDate);
        } else {
            return new ArrayList<>(logRecords);
        }

    }

    private List<LogData> filterByDateRange(String fromDate, String toDate) {
        OffsetDateTime fromDateTime = castFromDate(fromDate);
        OffsetDateTime toDateTime = castToDate(toDate);

        return logRecords.stream()
            .filter(record -> !record.getTimestamp().isBefore(fromDateTime) &&
                !record.getTimestamp().isAfter(toDateTime))
            .collect(Collectors.toList());
    }

    private List<LogData> filterOnlyFromDate(String fromDate) {
        OffsetDateTime fromDateTime = castFromDate(fromDate);

        return logRecords.stream()
            .filter(record -> !record.getTimestamp().isBefore(fromDateTime))
            .collect(Collectors.toList());
    }

    private List<LogData> filterOnlyToDate(String toDate) {
        OffsetDateTime toDateTime = castToDate(toDate);

        return logRecords.stream()
            .filter(record -> !record.getTimestamp().isAfter(toDateTime))
            .collect(Collectors.toList());
    }

    private OffsetDateTime castFromDate(String fromDate) {
        return LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE)
            .atStartOfDay()
            .atOffset(ZoneOffset.UTC);
    }

    private OffsetDateTime castToDate(String toDate) {
        return LocalDate.parse(toDate, DateTimeFormatter.ISO_DATE)
            .atTime(23, 59)
            .atOffset(ZoneOffset.UTC);
    }
}
