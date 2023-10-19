package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class Task2Test {
    @Test
    @DisplayName("Rectangle's area test")
    void rectangleAreaTest() {
        Rectangle rect = new Rectangle(20, 10);

        assertThat(rect.area()).isEqualTo(200);
    }

    @Test
    @DisplayName("Rectangle set width and and height test")
    void rectangleSetTest() {
        Rectangle rect = new Rectangle(1, 2);
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Square's area test")
    void SquareAreaTest() {
        Rectangle rect = new Square(20);

        assertThat(rect.area()).isEqualTo(400);
    }

    @Test
    @DisplayName("Square set width and height test")
    void SquareSetTest() {
        Rectangle rect = new Square(20);

        rect = rect.setHeight(5);
        rect = rect.setWidth(10);

        assertThat(rect.area()).isEqualTo(100);
    }
}
