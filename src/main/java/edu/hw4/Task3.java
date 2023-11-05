package edu.hw4;

import edu.hw4.Animal.Type;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class Task3 {
    private Task3() {}

    public static Map<Type, Long> collectAnimalsByType(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }
}
