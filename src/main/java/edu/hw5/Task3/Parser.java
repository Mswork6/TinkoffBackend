package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public abstract class Parser {
    private Parser next;

    public Parser setNextParser(Parser parser) {
        this.next = parser;
        return next;
    }

    public abstract Optional<LocalDate> parseDate(@NotNull String string);

    protected Optional<LocalDate> parseNext(String string) {
        if (next == null) {
            return Optional.empty();
        }
        return next.parseDate(string);
    }
}
