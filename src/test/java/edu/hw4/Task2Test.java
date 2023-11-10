package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @Test
    public void findHeaviestAnimalInEmptyCollectionTest() {
        assertThrows(IllegalArgumentException.class, () -> Task2.findHeaviestAnimals(Collections.emptyList(), 1));
    }

    @Test
    public void findHeaviestAnimalsTest() {
        List<Animal> receivedAnswer = Task2.findHeaviestAnimals(List.of(
            new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
            new Animal("Archi", Animal.Type.DOG, Animal.Sex.M, 5, 120, 15, false),
            new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Собака-улыбака", Animal.Type.DOG, Animal.Sex.M, 1, 120, 15, false)),
            2);

        assertThat(receivedAnswer.get(0).name()).isEqualTo("Archi");
        assertThat(receivedAnswer.get(1).name()).isEqualTo("Собака-улыбака");
    }
}
