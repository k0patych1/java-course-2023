package edu.project3.services.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class TimeParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss",
        Locale.ENGLISH);

    public static final int SHIFT_TO_DATE_PARSE = 6;

    private TimeParser() {}

    public static LocalDateTime parse(String s) {
        int startIndexTime = s.indexOf("[");
        int endIndexTime = s.indexOf("]");

        String dateString = s.substring(startIndexTime + 1, endIndexTime - SHIFT_TO_DATE_PARSE);

        return LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
    }
}
