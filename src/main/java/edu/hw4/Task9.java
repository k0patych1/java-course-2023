package edu.hw4;

import java.util.Collection;

public final class Task9 {

    private Task9() {}

    public static int sumPaws(Collection<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

}
