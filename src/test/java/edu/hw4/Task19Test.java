package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static edu.hw4.Task19.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task19Test {
    @Test
    public void findInvalidAnimalsTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal bird = new Animal("Голубь", Animal.Type.BIRD, Animal.Sex.F, -1, -2, 2, false);
        Animal fish = new Animal("Рыба фугу", Animal.Type.FISH, Animal.Sex.M, 1, 3, -1, false);

        List<Animal> animals = List.of(cat, dog, bird, fish);

        Map<String, Set<ValidationError>> result = findInvalidAnimals(animals);
        Map<String, Set<ValidationError>> expected = new HashMap<>();
        expected.put("Голубь", Set.of(ValidationError.INVALID_AGE, ValidationError.INVALID_HEIGHT));
        expected.put("Рыба фугу", Set.of(ValidationError.INVALID_WEIGHT));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void findInvalidAnimalsWithNoAnimalsTest() {
        List<Animal> animals = Collections.emptyList();

        Map<String, Set<ValidationError>> invalidAnimals = findInvalidAnimals(animals);

        assertThat(invalidAnimals).isEqualTo(Collections.emptyMap());
    }
}
