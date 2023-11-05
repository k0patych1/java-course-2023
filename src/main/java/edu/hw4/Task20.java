package edu.hw4;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class Task20 {
    private Task20() {}

    public static Map<String, String> findInvalidAnimalsStringVersion(Collection<Animal> animals) {
        return animals.stream()
            .filter(Task19::hasErrors)
            .collect(Collectors.toMap(Animal::name, animal ->
                Task19.getErrors(animal).stream()
                .map(Task19.ValidationError::toString)
                .collect(Collectors.joining("\n"))));
    }
}
