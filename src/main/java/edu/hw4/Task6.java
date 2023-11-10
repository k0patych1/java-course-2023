package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Task6 {
    private Task6() {}

    public static Map<Animal.Type, Animal> findHeaviestAnimalsInEveryType(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingDouble(Animal::weight))
            ));
    }
}
