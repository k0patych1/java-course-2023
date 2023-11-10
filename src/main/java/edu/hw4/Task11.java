package edu.hw4;

import java.util.Collection;
import java.util.List;

public final class Task11 {
    public static final int MINIMAL_HEIGHT = 100;

    private Task11() {}

    public static List<Animal> findBitingAnimalsAboveHeight(Collection<Animal> animals) {
        return animals.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > MINIMAL_HEIGHT)
            .toList();
    }
}
