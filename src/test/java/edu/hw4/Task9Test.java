package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task9Test {
    @Test
    public void calculateSumPawsTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 2, 8, 2, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 5, 15, 20, true);

        List<Animal> animals = List.of(cat, bird, dog);

        assertThat(Task9.sumPaws(animals)).isEqualTo(10);
    }

    @Test
    public void calculateSumPawsEmptyTest() {
        assertThat(Task9.sumPaws(Collections.emptyList())).isEqualTo(0);
    }
}
