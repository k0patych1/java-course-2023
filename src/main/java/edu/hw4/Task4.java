package edu.hw4;

import java.util.Collection;
import java.util.Comparator;

public final class Task4 {
    private Task4() {}

    public static Animal findLongestAnimalsName(Collection<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }
}
