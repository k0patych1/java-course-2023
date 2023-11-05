package edu.hw4;

import java.util.Collection;
import java.util.Comparator;

public final class Task7 {
    private Task7() {}

    public static Animal findKOldestAnimal(Collection<Animal> animals, int k) {
        if (animals.isEmpty() || k > animals.size()) {
            throw new IllegalArgumentException("There are not so many elements in the collection");
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .toList()
            .get(k - 1);
    }
}
