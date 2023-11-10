package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task10Test {
    @Test
    public void findAnimalsWithMismatchedAgeAndPawsTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 4, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 15, 20, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false);
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 3, 1, false);

        List<Animal> animals = List.of(cat, dog, bird, fish);

        List<Animal> actual = Task10.findAnimalsWithMismatchedAgeAndPaws(animals);
        List<Animal> expected = List.of(dog, bird, fish);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAnimalsWithMismatchedAgeAndPawsEmptyTest() {
        assertThat(Task10.findAnimalsWithMismatchedAgeAndPaws(Collections.emptyList()))
            .isEqualTo(Collections.emptyList());
    }
}
