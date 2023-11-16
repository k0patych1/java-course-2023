package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @Test
    public void findAllFridays13thInYearIncorrectTest() {
        assertThrows(IllegalArgumentException.class, () -> Task2.findAllFridays13thInYear(-1));
    }

    @Test
    public void findAllFridays13thInYearTest() {
        List<LocalDate> receivedAnswer = Task2.findAllFridays13thInYear(1925);

        assertThat(receivedAnswer.size()).isEqualTo(3);
        assertTrue(receivedAnswer.contains(LocalDate.of(1925, 2, 13)));
        assertTrue(receivedAnswer.contains(LocalDate.of(1925, 3, 13)));
        assertTrue(receivedAnswer.contains(LocalDate.of(1925, 11, 13)));
    }

    @Test
    public void findAllFridays13thInYearAdditionalTest() {
        List<LocalDate> receivedAnswer = Task2.findAllFridays13thInYear(2024);

        assertThat(receivedAnswer.size()).isEqualTo(2);
        assertTrue(receivedAnswer.contains(LocalDate.of(2024, 9, 13)));
        assertTrue(receivedAnswer.contains(LocalDate.of(2024, 12, 13)));
    }

    @Test
    public void findNextFriday13th() {
        LocalDate receivedAnswer = Task2.findNextFriday13th(LocalDate.now());

        assertThat(receivedAnswer).isEqualTo(LocalDate.of(2024, 9, 13));
    }
}
