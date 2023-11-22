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
    public static String getAverageVisitingTime(String[] array) {
        Duration totalDuration = Duration.ZERO;
        int counter = 0;

        for (String string : array) {
            Matcher matcher = TIME_PATTERN.matcher(string);

            if (matcher.find()) {
                String firstDate = String.format(DATE_FORMAT, matcher.group(1), matcher.group(2));
                String secondDate = String.format(DATE_FORMAT, matcher.group(3), matcher.group(4));

                LocalDateTime start = LocalDateTime.parse(firstDate);
                LocalDateTime end = LocalDateTime.parse(secondDate);


                Duration interval = Duration.between(start, end);
                totalDuration = totalDuration.plus(interval);
                counter++;
            }
        }
        long average = 0;
        try {
            average = Math.abs(totalDuration.dividedBy(counter).toMinutes());
        } catch (ArithmeticException ignored) {
        }

        long hours = average / MINUTES_IN_HOUR;
        long minutes = average % MINUTES_IN_HOUR;

        return String.format("%dч %dм", hours, minutes);
    }

}
