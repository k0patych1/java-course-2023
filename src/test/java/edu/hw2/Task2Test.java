package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class Task2Test {
    @Test
    @DisplayName("Rectangle's area test")
    void rectangleAreaTest() {
        Rectangle rect = new Rectangle(20, 10);

        assertThat(rect.area()).isEqualTo(200);
    }

    @Test
    @DisplayName("Square's area test")
    void squareAreaTest() {
        Rectangle rect = new Square(20);

        assertThat(rect.area()).isEqualTo(400);
    }

    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Square and rectangle test from the condition description")
    void rectangleAreaTest(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }
}
