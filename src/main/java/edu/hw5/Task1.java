package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task1 {
    private final static String REGEX = "^(\\d{4}-\\d{2}-\\d{2}), (\\d{2}:\\d{2}) - "
        + "(\\d{4}-\\d{2}-\\d{2}), (\\d{2}:\\d{2})$";
    private final static Pattern TIME_PATTERN = Pattern.compile(REGEX);
    private final static int MINUTES_IN_HOUR = 60;
    private final static String DATE_FORMAT = "%sT%s:00";

    @SuppressWarnings("MagicNumber")
    public static String getTimeDifference(String[] array) {
        int total = 0;
        int counter = 0;

        for (String string : array) {
            Matcher matcher = TIME_PATTERN.matcher(string);

            if (matcher.find()) {
                String firstDate = String.format(DATE_FORMAT, matcher.group(1), matcher.group(2));
                String secondDate = String.format(DATE_FORMAT, matcher.group(3), matcher.group(4));

                LocalDateTime start = LocalDateTime.parse(firstDate);
                LocalDateTime end = LocalDateTime.parse(secondDate);

                int difference = (int) Math.abs(Duration.between(start, end).toMinutes());
                total += difference;
                counter++;
            }
        }
        int average = 0;
        try {
            average = total / counter;
        } catch (ArithmeticException ignored) {
        }

        int hours = average / MINUTES_IN_HOUR;
        int minutes = average % MINUTES_IN_HOUR;

        return String.format("%dч %dм", hours, minutes);
    }
}
