package edu.hw4;

import java.util.Collection;

public final class Task17 {
    private Task17() {}

    public static boolean spidersBiteMoreThanDogs(Collection<Animal> animals) {
        long spiderBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER)
            .filter(Animal::bites)
            .count();

        long dogBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .filter(Animal::bites)
            .count();

        return spiderBites > dogBites;
    }
}
