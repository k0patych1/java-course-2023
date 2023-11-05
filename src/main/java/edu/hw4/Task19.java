package edu.hw4;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Task19 {
    private Task19() {}

    public static Map<String, Set<ValidationError>> findInvalidAnimals(Collection<Animal> animals) {
        return animals.stream()
            .filter(Task19::hasErrors)
            .collect(Collectors.toMap(Animal::name, Task19::getErrors));
    }

    public static boolean hasErrors(Animal animal) {
        Set<ValidationError> errors = getErrors(animal);
        return !errors.isEmpty();
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
