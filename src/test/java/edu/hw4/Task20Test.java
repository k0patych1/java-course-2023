package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static edu.hw4.Task20.findInvalidAnimalsStringVersion;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task20Test {
    @Test
    public void findInvalidAnimalsStringVersionWithNoAnimalsTest() {
        List<Animal> animals = Collections.emptyList();

        Map<String, String> invalidAnimals = findInvalidAnimalsStringVersion(animals);

        assertThat(invalidAnimals).isEqualTo(Collections.emptyMap());
    }

    @Test
    public void findInvalidAnimalsTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal bird = new Animal("Голубь", Animal.Type.BIRD, Animal.Sex.F, -1, -2, 2, false);
        Animal fish = new Animal("Рыба фугу", Animal.Type.FISH, Animal.Sex.M, 1, 3, -1, false);

        List<Animal> animals = List.of(cat, dog, bird, fish);

        Map<String, String> result = findInvalidAnimalsStringVersion(animals);

        assertThat(result.size()).isEqualTo(2);
        assertTrue(result.get("Голубь").contains("Height : value must be positive"));
        assertTrue(result.get("Голубь").contains("Age : value must be positive"));
        assertThat(result.get("Рыба фугу")).isEqualTo("Weight : value must be positive");
    }
}
