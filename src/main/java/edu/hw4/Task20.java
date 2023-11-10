package edu.hw4;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class Task20 {
    private Task20() {}

    public static Map<String, String> findInvalidAnimalsStringVersion(Collection<Animal> animals) {
        return animals.stream()
            .flatMap(animal -> Task19.getErrors(animal).stream()
                .map(error -> Map.entry(animal.name(), error.toString())))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1 + "\n" + v2));
    }
}
