package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task11Test {
    @Test
    public void findBitingAnimalsAboveHeightTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 100, 5, true);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 5, 101, 20, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, true);
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 3, 1, false);

        List<Animal> animals = List.of(cat, dog, bird, fish);

        assertThat(Task11.findBitingAnimalsAboveHeight(animals))
            .isEqualTo(Collections.singletonList(dog));
    }

    @Test
    public void findBitingAnimalsAboveHeightEmptyTest() {
        List<Animal> actual = Task11.findBitingAnimalsAboveHeight(Collections.emptyList());

        assertThat(actual).isEqualTo(Collections.emptyList());
    }
}
