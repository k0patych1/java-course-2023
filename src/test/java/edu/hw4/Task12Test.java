package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task12Test {
    @Test
    void countAnimalsWithWeightGreaterThanHeightTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 5, 15, 20, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false);
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 3, 1, false);

        List<Animal> animals = List.of(cat, dog, bird, fish);

        assertThat(Task12.countAnimalsWithWeightGreaterThanHeight(animals))
            .isEqualTo(1);
    }

    @Test
    public void countAnimalsWithWeightGreaterThanHeightEmptyTest() {
        assertThat(Task12.countAnimalsWithWeightGreaterThanHeight(Collections.emptyList()))
            .isEqualTo(0);
    }

    @Test
    public void countAnimalsWithWeightGreaterThanHeightNoMatchTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 2, 8, 2, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 5, 15, 15, true);

        List<Animal> animals = List.of(cat, bird, dog);

        assertThat(Task12.countAnimalsWithWeightGreaterThanHeight(animals))
            .isEqualTo(0);
    }
}
