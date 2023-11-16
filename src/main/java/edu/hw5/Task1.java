package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class Task1 {
    private Task1() {}

    public static Duration calculateAverageVisitDuration(List<String> durations) {
        Duration totalDuration = Duration.ZERO;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        for (String strDuration : durations) {
            String[] startEnd = strDuration.split(" - ");

            if (startEnd.length != 2) {
                throw new IllegalArgumentException("Can't parse string argument to start and end time");
            }

            LocalDateTime start = LocalDateTime.parse(startEnd[0], formatter);
            LocalDateTime end = LocalDateTime.parse(startEnd[1], formatter);

            Duration duration = Duration.between(start, end);
            totalDuration = totalDuration.plus(duration);
        }

        return totalDuration.dividedBy(durations.size());
    }
}
