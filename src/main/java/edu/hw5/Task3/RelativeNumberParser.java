package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class RelativeNumberParser extends Parser {
    private final static String PATTERN = "^1 day ago$|^(([2-9]|[1-9][0-9]+) days ago)$";

    @Override
    public Optional<LocalDate> parseDate(@NotNull String dateString) {
        if (dateString.matches(PATTERN)) {
            int days = Integer.parseInt(dateString.split(" ")[0]);
            LocalDate date = LocalDate.now().minusDays(days);
            return Optional.of(date);
        }
        return parseNext(dateString);
    }
}
