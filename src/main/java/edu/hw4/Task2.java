package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public final class Task2 {
    private Task2() {}

    public static List<Animal> findHeaviestAnimals(Collection<Animal> animals, int k) {
        if (animals.isEmpty() || k > animals.size()) {
            throw new IllegalArgumentException("There are not so many elements in the collection");
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }
}
