package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    public void calculateAverageVisitDurationIncorrectDuration() {
        List<String> times = new ArrayList<>();
        times.add("2022-03-12, 20:20");

        assertThrows(IllegalArgumentException.class, () -> Task1.calculateAverageVisitDuration(times));
    }

    @Test
    public void calculateAverageVisitDurationIncorrectStartTime() {
        List<String> times = new ArrayList<>();
        times.add("2022-03-12, 20:60 - 2022-03-12, 23:50");

        assertThrows(DateTimeParseException.class, () -> Task1.calculateAverageVisitDuration(times));
    }

    @Test
    public void calculateAverageVisitDurationIncorrectEndDate() {
        List<String> times = new ArrayList<>();
        times.add("2022-03-12, 20:20 - 22-03-12, 23:50");

        assertThrows(DateTimeParseException.class, () -> Task1.calculateAverageVisitDuration(times));
    }

    @Test
    public void calculateSingleVisitDuration() {
        List<String> times = new ArrayList<>();
        times.add("2022-03-12, 20:20 - 2022-03-12, 23:50");

        Duration duration = Task1.calculateAverageVisitDuration(times);

        assertThat(duration.toDays()).isEqualTo(0);
        assertThat(duration.toHours()).isEqualTo(3);
        assertThat(duration.toMinutesPart()).isEqualTo(30);
        assertThat(duration.toSecondsPart()).isEqualTo(0);
    }

    @Test
    public void calculateAverageVisitDuration() {
        List<String> times = new ArrayList<>();
        times.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        times.add("2022-04-01, 21:30 - 2022-04-02, 01:20");

        Duration duration = Task1.calculateAverageVisitDuration(times);

        assertThat(duration.toDays()).isEqualTo(0);
        assertThat(duration.toHours()).isEqualTo(3);
        assertThat(duration.toMinutesPart()).isEqualTo(40);
        assertThat(duration.toSecondsPart()).isEqualTo(0);
    }
}
