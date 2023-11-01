package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    @Test
    public void backwardIteratorTest() {
        var it = new BackwardIterator<>(List.of(1, 2, 3));

        assertTrue(it.hasNext());
        assertThat(it.next()).isEqualTo(3);
        assertTrue(it.hasNext());
        assertThat(it.next()).isEqualTo(2);
        assertTrue(it.hasNext());
        assertThat(it.next()).isEqualTo(1);
        assertFalse(it.hasNext());
    }

    @Test
    public void backwardIteratorInEmptyCollectionTest() {
        var it = new BackwardIterator<>(List.of());

        assertFalse(it.hasNext());
    }
}
