package edu.hw3.task5;

import java.util.Comparator;

public class PersonComparatorDesc implements Comparator<Person> {
    @Override
    public int compare(Person person2, Person person1) {
        if (person1.surname() == null && person2.surname() == null) {
            return person1.name().compareTo(person2.name());
        }
        if (person1.surname() == null) {
            return person2.name().compareTo(person2.surname());
        }
        if (person2.surname() == null) {
            return person1.surname().compareTo(person2.name());
        }

        return person1.surname().compareTo(person2.surname());
    }
}
