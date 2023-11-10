package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    public void emptyListTest() {
        assertThat(Task6.findHeaviestAnimalsInEveryType(Collections.emptyList())).isEqualTo(Collections.emptyMap());
    }

    @Test
    public void singleAnimalTest() {
        Animal cat = new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, false);
        List<Animal> animals = Collections.singletonList(cat);

        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, cat);

        assertThat(Task6.findHeaviestAnimalsInEveryType(animals)).isEqualTo(expected);
    }

    @Test
    public void multipleAnimalsSameTypeTest() {
        Animal cat1 = new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, false);
        Animal cat2 = new Animal("Whiskers", Animal.Type.CAT, Animal.Sex.M, 3, 12, 6, true);
        List<Animal> animals = Arrays.asList(cat1, cat2);

        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, cat2);

        assertThat(Task6.findHeaviestAnimalsInEveryType(animals)).isEqualTo(expected);
    }

    @Test
    public void multipleAnimalsDifferentTypesTest() {
        Animal cat1 = new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, false);
        Animal cat2 = new Animal("Whiskers", Animal.Type.CAT, Animal.Sex.M, 3, 12, 6, true);
        Animal dog = new Animal("Spot", Animal.Type.DOG, Animal.Sex.M, 3, 15, 10, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 2, false);
        Animal fish = new Animal("Goldie", Animal.Type.FISH, Animal.Sex.F, 2, 1, 0, false);
        List<Animal> animals = List.of(cat1, cat2, dog, bird, fish);

        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, cat2);
        expected.put(Animal.Type.DOG, dog);
        expected.put(Animal.Type.BIRD, bird);
        expected.put(Animal.Type.FISH, fish);

        assertThat(Task6.findHeaviestAnimalsInEveryType(animals)).isEqualTo(expected);
    }
}
