package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {
    @Test
    public void emptyCollectionTest() {
        assertThrows(IllegalArgumentException.class, () -> Task7.findKOldestAnimal(Collections.emptyList(), 1));
    }

    @Test
    public void kGreaterThanSizeTest() {
        Animal cat = new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, false);
        List<Animal> animals = new ArrayList<>();
        animals.add(cat);
        assertThrows(IllegalArgumentException.class, () -> Task7.findKOldestAnimal(animals, 2));
    }

    @Test
    public void findHeaviestAnimalTest() {
        assertThat(Task7.findKOldestAnimal(List.of(
                new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
                new Animal("Archi", Animal.Type.DOG, Animal.Sex.M, 5, 120, 15, false),
                new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
                new Animal("Собака-улыбака", Animal.Type.DOG, Animal.Sex.M, 1, 120, 15, false)),
            1).name())
            .isEqualTo("Emos");
    }

    @Test
    public void findKHeaviestAnimalTest() {
        assertThat(Task7.findKOldestAnimal(List.of(
                new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
                new Animal("Archi", Animal.Type.DOG, Animal.Sex.M, 5, 120, 15, false),
                new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
                new Animal("Собака-улыбака", Animal.Type.DOG, Animal.Sex.M, 1, 120, 15, false)),
            2).name())
            .isEqualTo("Archi");
    }
}
