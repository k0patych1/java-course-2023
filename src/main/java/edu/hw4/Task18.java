package edu.hw4;

import java.util.Collection;
import java.util.Comparator;

public final class Task18 {
    private Task18() {}

    public static Animal findHeaviestFish(Collection<Collection<Animal>> animalLists) {
        return animalLists.stream()
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }
}
