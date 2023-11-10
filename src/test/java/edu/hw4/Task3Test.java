package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    public void emptyListTest() {
        assertThat(Task3.collectAnimalsByType(Collections.emptyList())).isEqualTo(Collections.emptyMap());
    }

    @Test
    public void singleAnimalTest() {
        Animal cat = new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, false);
        List<Animal> animals = Collections.singletonList(cat);

        Map<Animal.Type, Long> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, 1L);

        assertThat(Task3.collectAnimalsByType(animals)).isEqualTo(expected);
    }

    @Test
    public void multipleAnimalsSameTypeTest() {
        Animal cat1 = new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, false);
        Animal cat2 = new Animal("Whiskers", Animal.Type.CAT, Animal.Sex.M, 3, 12, 6, true);
        List<Animal> animals = List.of(cat1, cat2);

        Map<Animal.Type, Long> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, 2L);

        assertThat(Task3.collectAnimalsByType(animals)).isEqualTo(expected);
    }

    @Test
    public void multipleAnimalsDifferentTypesTest() {
        Animal cat = new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, false);
        Animal dog = new Animal("Spot", Animal.Type.DOG, Animal.Sex.M, 3, 15, 10, true);
        Animal bird = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 2, false);
        Animal fish = new Animal("Goldie", Animal.Type.FISH, Animal.Sex.F, 2, 1, 0, false);
        List<Animal> animals = List.of(cat, dog, bird, fish);

        Map<Animal.Type, Long> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, 1L);
        expected.put(Animal.Type.DOG, 1L);
        expected.put(Animal.Type.BIRD, 1L);
        expected.put(Animal.Type.FISH, 1L);

        assertThat(Task3.collectAnimalsByType(animals)).isEqualTo(expected);
    }
}

