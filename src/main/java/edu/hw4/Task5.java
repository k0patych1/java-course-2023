package edu.hw4;

import edu.hw4.Animal.Sex;
import java.util.Collection;

public final class Task5 {
    private Task5() {}

    public static Sex findSexWithMoreAnimals(Collection<Animal> animals) {
        long malesCount = animals.stream()
            .filter(animal -> animal.sex() == Sex.M)
            .count();

        long femalesCount = animals.stream()
            .filter(animal -> animal.sex() == Sex.F)
            .count();

        if (malesCount > femalesCount) {
            return Sex.M;
        } else if (malesCount < femalesCount) {
            return Sex.F;
        } else {
            return null;
        }
    }
}
