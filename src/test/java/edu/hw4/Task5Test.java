package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task5Test {
    @Test
    public void emptyListTest() {
        assertNull(Task5.findSexWithMoreAnimals(Collections.emptyList()));
    }

    @Test
    public void femaleTest() {
        assertThat(Task5.findSexWithMoreAnimals(List.of(
            new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
            new Animal("Archi", Animal.Type.DOG, Animal.Sex.F, 5, 120, 15, false),
            new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Собака-улыбака", Animal.Type.DOG, Animal.Sex.F, 1, 120, 15, false))))
            .isEqualTo(Animal.Sex.F);
    }

    @Test
    public void maleTest() {
        assertThat(Task5.findSexWithMoreAnimals(List.of(
            new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
            new Animal("Archi", Animal.Type.DOG, Animal.Sex.M, 5, 120, 15, false),
            new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Собака-улыбака", Animal.Type.DOG, Animal.Sex.M, 1, 120, 15, false))))
            .isEqualTo(Animal.Sex.M);
    }

    @Test
    public void femaleAndMaleEqualTest() {
        assertNull(Task5.findSexWithMoreAnimals(List.of(
            new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
            new Animal("Archi", Animal.Type.DOG, Animal.Sex.M, 5, 120, 15, false),
            new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Собака-улыбака", Animal.Type.DOG, Animal.Sex.F, 1, 120, 15, false))));
    }
}
