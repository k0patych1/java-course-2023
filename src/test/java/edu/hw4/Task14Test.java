package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task14Test {
    @Test
    public void hasDogWithHeightGreaterThanTrueTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false);
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 3, 1, false);

        List<Animal> animals = List.of(cat, dog, bird, fish);

        assertTrue(Task14.hasDogWithHeightGreaterThan(animals, 10));
    }

    @Test
    public void hasDogWithHeightGreaterThanFalseTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false);
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 3, 1, false);

        List<Animal> animals = List.of(cat, dog, bird, fish);

        assertFalse(Task14.hasDogWithHeightGreaterThan(animals, 20));
    }

    @Test
    public void hasDogWithHeightGreaterThanWithNoDogsTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false);
        Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 3, 1, false);

        List<Animal> animals = List.of(cat, bird, fish);

        assertFalse(Task14.hasDogWithHeightGreaterThan(animals, 10));
    }
}
