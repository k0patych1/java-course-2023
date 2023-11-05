package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public final class Task6 {
    private Task6() {}

    public static Map<Animal.Type, Animal> findHeaviestAnimalsInEveryType(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.maxBy(Comparator.comparingDouble(Animal::weight))))
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().orElse(null)));
    }
}
