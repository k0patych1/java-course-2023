package edu.hw4;


import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task8Test {
    @Test
    public void findHeaviestAnimalNullTest() {
        Collection<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 5, 15, 20, true));

        assertNull(Task8.findHeaviestAnimalBelowHeight(animals, 5));
    }

    @Test
    public void findHeaviestAnimalBelowHeightTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 2, 8, 2, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 5, 15, 20, true);

        List<Animal> animals = List.of(cat, bird, dog);

        Animal actual = Task8.findHeaviestAnimalBelowHeight(animals, 12);

        assertThat(actual).isEqualTo(cat);
    }
}
