package edu.hw5;

import edu.hw5.Task3.Parser;
import edu.hw5.Task3.RelativeNonNumberParser;
import edu.hw5.Task3.RelativeNumberParser;
import edu.hw5.Task3.YearMonthDayParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    @DisplayName("Проверка корректных данных")
    void checkCorrectData() {
        // given
        String[] dateStrings = {"2020-10-10", "2020-12-2", "1/3/1976", "1/3/20", "tomorrow", "today",
            "yesterday", "1 day ago", "2234 days ago"};

        Parser parser = new YearMonthDayParser();
        parser.setNextParser(new RelativeNonNumberParser())
            .setNextParser(new RelativeNumberParser());
        // when

        // then
        for (String date: dateStrings){
            assertTrue(parser.parseDate(date).isPresent());
        }
    }

    @Test
    @DisplayName("Проверка некорректных данных")
    void checkIncorrectData() {
        // given
        String[] dateStrings = {"2020-20-10", "2020-12-35", "1/3-1976", "32/3/2020", "tomorow",
            "", "6 day ago", "2234 day ago", "1 days ago"};

        Parser parser = new YearMonthDayParser();
        parser.setNextParser(new RelativeNonNumberParser())
            .setNextParser(new RelativeNumberParser());
        // when

        // then
        for (String date: dateStrings){
            assertFalse(parser.parseDate(date).isPresent());
        }
    }
}
