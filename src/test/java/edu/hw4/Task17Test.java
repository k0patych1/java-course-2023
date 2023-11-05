package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task17Test {
    @Test
    public void spidersBiteMoreThanDogsTrueTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal spider1 = new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.F, 4, 8, 2, true);
        Animal spider2 = new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 3, true);
        Animal spider3 = new Animal("Spider3", Animal.Type.SPIDER, Animal.Sex.F, 3, 7, 4, true);

        List<Animal> animals = List.of(cat, dog, spider1, spider2, spider3);

        assertTrue(Task17.spidersBiteMoreThanDogs(animals));
    }

    @Test
    public void spidersBiteMoreThanDogsFalseTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal spider = new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.F, 4, 8, 2, true);

        List<Animal> animals = List.of(cat, dog, spider);

        assertFalse(Task17.spidersBiteMoreThanDogs(animals));
    }

    @Test
    public void spidersBiteMoreThanDogsWithNoSpidersTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);
        Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);

        List<Animal> animals = List.of(cat, dog);

        assertFalse(Task17.spidersBiteMoreThanDogs(animals));
    }

    @Test
    public void spidersBiteMoreThanDogsWithNoDogsTest() {
        Animal spider1 = new Animal("Spider1", Animal.Type.SPIDER, Animal.Sex.F, 4, 8, 2, true);
        Animal spider2 = new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 3, true);
        Animal spider3 = new Animal("Spider3", Animal.Type.SPIDER, Animal.Sex.F, 3, 7, 4, true);

        List<Animal> animals = List.of(spider1, spider2, spider3);

        assertTrue(Task17.spidersBiteMoreThanDogs(animals));
    }

    @Test
    public void spidersBiteMoreThanDogsWithNoSpidersAndDogsTest() {
        Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, false);

        List<Animal> animals = List.of(cat);

        assertFalse(Task17.spidersBiteMoreThanDogs(animals));
    }
}
