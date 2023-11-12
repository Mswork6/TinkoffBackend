package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class RelativeNonNumberParser extends Parser {

    @Override
    public Optional<LocalDate> parseDate(@NotNull String dateString) {
        return switch (dateString) {
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "today" -> Optional.of(LocalDate.now());
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            default -> parseNext(dateString);
        };
    }
}
