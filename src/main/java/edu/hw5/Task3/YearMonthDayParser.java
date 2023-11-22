package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class YearMonthDayParser extends Parser {
    private static final String[] PATTERNS = {"yyyy-MM-dd", "yyyy-MM-d",
        "yyyy-M-dd", "yyyy-M-d", "M/d/yyyy", "M/d/yy"};
    private static final String FIRST_REGEXP = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    private static final String SECOND_REGEXP = "^\\d{1,2}/\\d{1,2}/\\d{2,4}$";

    @Override
    public Optional<LocalDate> parseDate(@NotNull String dateString) {
        if (dateString.matches(FIRST_REGEXP) || dateString.matches(SECOND_REGEXP)) {
            for (String pattern : PATTERNS) {
                try {
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
                    return Optional.of(date);
                } catch (Exception ignored) {
                }
            }
        }
        return parseNext(dateString);
    }
}
