package edu.hw4;

import java.util.Collection;

public final class Task12 {
    private Task12() {}

    public static Long countAnimalsWithWeightGreaterThanHeight(Collection<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }
}
