package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task18Test {
    @Test
    public void findHeaviestFishTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false);
        Animal fish1 = new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 1, 3, 4, false);
        Animal fish2 = new Animal("Fish2", Animal.Type.FISH, Animal.Sex.F, 2, 6, 3, false);
        Animal fish3 = new Animal("Fish3", Animal.Type.FISH, Animal.Sex.M, 3, 9, 6, false);
        Animal fish4 = new Animal("Fish4", Animal.Type.FISH, Animal.Sex.F, 4, 12, 5, false);

        List<Animal> animals1 = List.of(cat, dog, bird);
        List<Animal> animals2 = List.of(fish1, fish2, fish3);
        List<Animal> animals3 = List.of(fish4);

        List<Collection<Animal>> animalLists = List.of(animals1, animals2, animals3);

        Animal heaviestFish = Task18.findHeaviestFish(animalLists);

        assertThat(heaviestFish).isEqualTo(fish3);
    }

    @Test
    public void findHeaviestFishWithNoFishesTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal bird = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 4, 8, 2, false);

        List<Animal> animals1 = List.of(cat, dog, bird);
        List<Animal> animals2 = Collections.emptyList();

        List<Collection<Animal>> animalLists = List.of(animals1, animals2);

        assertNull(Task18.findHeaviestFish(animalLists));
    }

    @Test
    public void findHeaviestFishWithNoAnimalListsTest() {
        assertNull(Task18.findHeaviestFish(Collections.emptyList()));
    }
}
