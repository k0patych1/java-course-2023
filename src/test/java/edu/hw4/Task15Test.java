package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task15Test {
    @Test
    public void sumWeightTest() {
        Animal cat1 = new Animal("Cat1", Animal.Type.CAT, Animal.Sex.M, 10, 12, 8, false);
        Animal cat2 = new Animal("Cat2", Animal.Type.CAT, Animal.Sex.F, 2, 9, 6, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 5, 18, 12, false);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 3, 5, 2, false);
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 2, 3, 1, false);

        List<Animal> animals = List.of(cat1, cat2, dog, bird, fish);

        Map<Animal.Type, Integer> result = Task15.sumWeight(animals, 2, 4);
        Map<Animal.Type, Integer> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, 6);
        expected.put(Animal.Type.FISH, 1);
        expected.put(Animal.Type.BIRD, 2);

        assertThat(result).isEqualTo(expected);
    }
}
