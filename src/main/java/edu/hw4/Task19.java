package edu.hw4;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Task19 {
    private Task19() {}

    public static Map<String, Set<ValidationError>> findInvalidAnimals(Collection<Animal> animals) {
        return animals.stream()
            .flatMap(animal -> {
                Set<ValidationError> errors = getErrors(animal);
                return errors.isEmpty()
                    ? Stream.empty()
                    : Stream.of(new AbstractMap.SimpleEntry<>(animal.name(), errors));
            })
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Set<ValidationError> getErrors(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();

        if (animal.age() <= 0) {
            errors.add(ValidationError.INVALID_AGE);
        }

        if (animal.height() <= 0) {
            errors.add(ValidationError.INVALID_HEIGHT);
        }

        if (animal.weight() <= 0) {
            errors.add(ValidationError.INVALID_WEIGHT);
        }

        return errors;
    }

    public enum ValidationError {
        INVALID_AGE {
            @Override
            public String toString() {
                return "Age : value must be positive";
            }
        },
        INVALID_HEIGHT {
            @Override
            public String toString() {
                return "Height : value must be positive";
            }
        },
        INVALID_WEIGHT {
            @Override
            public String toString() {
                return "Weight : value must be positive";
            }
        }
    }
}
