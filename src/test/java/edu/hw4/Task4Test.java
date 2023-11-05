package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.List;
import edu.hw4.Animal.Sex;
import edu.hw4.Animal.Type;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test { @Test
    public void testCollectAnimalsByType() {
        Animal tom = new Animal("Tom", Type.CAT, Sex.M, 3, 30, 5, true);
        Animal jerry = new Animal("Jerry", Type.CAT, Sex.F, 2, 25, 4, false);
        Animal max = new Animal("Max", Type.DOG, Sex.M, 5, 50, 10, true);
        Animal bella = new Animal("Bella", Type.DOG, Sex.F, 4, 45, 9, false);
        Animal tweety = new Animal("Tweety", Type.BIRD, Sex.M, 1, 10, 0, false);
        Animal nemo = new Animal("Nemo", Type.FISH, Sex.M, 2, 5, 1, false);
        Animal charlotte = new Animal("Charlotte", Type.SPIDER, Sex.F, 1, 2, 0, true);

        List<Animal> animals = List.of(tom, jerry, max, bella, tweety, nemo, charlotte);

        assertThat(Task4.findLongestAnimalsName(animals)).isEqualTo(charlotte);
    }
}
