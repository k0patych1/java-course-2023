package edu.hw4;

import java.util.Collection;
import java.util.Comparator;

public final class Task8 {
    private Task8() {}

    public static Animal findHeaviestAnimalBelowHeight(Collection<Animal> animals, int height) {
        return animals.stream()
            .filter(animal -> animal.height() < height)
            .max(Comparator.comparingDouble(Animal::weight))
            .orElse(null);
    }
}
