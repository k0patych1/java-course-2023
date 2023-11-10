package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    public void sortEmptyCollectionTest() {
        assertThat(Task1.sortAnimalsAsc(Collections.emptyList())).isEqualTo(Collections.emptyList());
    }

    @Test
    public void sortAnimalsTest() {
        assertThat(Task1.sortAnimalsAsc(List.of(
            new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
            new Animal("Archi", Animal.Type.DOG, Animal.Sex.M, 5, 120, 15, false),
            new Animal("Aboba", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false))))
            .isEqualTo(List.of(
                new Animal("Aboba", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
                new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
                new Animal("Archi", Animal.Type.DOG, Animal.Sex.M, 5, 120, 15, false)));
    }

    @Test
    public void sortAnimalsWithSameHeightTest() {
        assertThat(Task1.sortAnimalsAsc(List.of(
            new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
            new Animal("Archi", Animal.Type.DOG, Animal.Sex.M, 5, 120, 15, false),
            new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Собака-улыбака", Animal.Type.DOG, Animal.Sex.M, 1, 120, 15, false))))
            .isEqualTo(List.of(
                new Animal("Золотая рыбка", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false),
                new Animal("Emos", Animal.Type.CAT, Animal.Sex.M, 9, 80, 8, true),
                new Animal("Archi", Animal.Type.DOG, Animal.Sex.M, 5, 120, 15, false),
                new Animal("Собака-улыбака", Animal.Type.DOG, Animal.Sex.M, 1, 120, 15, false)));
    }
}

