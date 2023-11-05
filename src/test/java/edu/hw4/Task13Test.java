package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task13Test {
    @Test
    public void getAnimalsWithMultiWordNames() {
        Animal cat = new Animal("Black Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Golden Retriever", Animal.Type.DOG, Animal.Sex.F, 5, 15, 20, true);
        Animal bird = new Animal("Blue Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false);
        Animal fish = new Animal("Nemo the Fish", Animal.Type.FISH, Animal.Sex.M, 1, 3, 1, false);

        List<Animal> animals = List.of(cat, dog, bird, fish);

        List<Animal> actual = Task13.getAnimalsWithMultiWordNames(animals);
        List<Animal> expected = Collections.singletonList(fish);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getAnimalsWithMultiWordNamesEmptyTest() {
        assertThat(Task13.getAnimalsWithMultiWordNames(Collections.emptyList()).size())
            .isEqualTo(0);
    }

    @Test
    public void getAnimalsWithMultiWordNamesNoMatchTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 2, 8, 2, false);
        Animal dog = new Animal("Golden Retriever", Animal.Type.DOG, Animal.Sex.F, 5, 15, 20, true);

        List<Animal> animals = List.of(cat, bird, dog);

        assertThat(Task13.getAnimalsWithMultiWordNames(animals).size())
            .isEqualTo(0);
    }
}
