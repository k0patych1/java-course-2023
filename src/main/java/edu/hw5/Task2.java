package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private Task2() {}

    public static final int UNLUCKY_NUMBER = 13;

    private static final int NUM_OF_MONTHS_IN_YEAR = 12;

    public static List<LocalDate> findAllFridays13thInYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("Year can't be negative");
        }

        List<LocalDate> fridays13thList = new ArrayList<>();

        for (int month = 1; month <= NUM_OF_MONTHS_IN_YEAR; ++month) {
            LocalDate date = LocalDate.of(year, month, UNLUCKY_NUMBER);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13thList.add(date);
            }
        }

        return fridays13thList;
    }

    public static LocalDate findNextFriday13th(LocalDate date) {
        TemporalAdjuster nextFriday13Adjuster = TemporalAdjusters.ofDateAdjuster(t -> {
            LocalDate nextDate = t;
            do {
                nextDate = nextDate.plusDays(1);
            } while (nextDate.getDayOfMonth() != UNLUCKY_NUMBER || nextDate.getDayOfWeek() != DayOfWeek.FRIDAY);

            return nextDate;
        });

        return date.with(nextFriday13Adjuster);
    }
}
