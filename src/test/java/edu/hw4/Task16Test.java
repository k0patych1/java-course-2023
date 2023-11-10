package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task16Test {
    @Test
    public void sortAnimalsTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog1 = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal dog2 = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 4, 20, 10, false);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false);
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 3, 1, false);

        List<Animal> animals = List.of(cat, dog1, dog2, bird, fish);

        List<Animal> result = Task16.sortAnimals(animals);
        List<Animal> expected = List.of(cat, dog1, dog2, bird, fish);

        assertThat(result).isEqualTo(expected);
    }
}
