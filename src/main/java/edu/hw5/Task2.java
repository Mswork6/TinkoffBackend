package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Task2 {
    private final static int DAY_OF_MONTH = 13;

    public static List<LocalDate> findFridaysOnThe13th(int year) {
        List<LocalDate> fridaysOnThe13th = new ArrayList<>();
        for (Month month : Month.values()) {
            LocalDate date = LocalDate.of(year, month, DAY_OF_MONTH);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridaysOnThe13th.add(date);
            }
        }
        return fridaysOnThe13th;
    }

    public static LocalDate findNextFridayThe13(LocalDate date) {
        LocalDate nextFridayThe13 = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (nextFridayThe13.getDayOfMonth() != DAY_OF_MONTH) {
            nextFridayThe13 = nextFridayThe13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFridayThe13;
    }
}
