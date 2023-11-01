package edu.hw3.task5;

import java.util.Comparator;

public class PersonComparatorAsc implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        if (person1.surname() == null && person2.surname() == null) {
            return person1.name().compareTo(person2.name());
        }
        if (person1.surname() == null) {
            return person1.name().compareTo(person2.surname());
        }
        if (person2.surname() == null) {
            return person1.surname().compareTo(person2.name());
        }

        return person1.surname().compareTo(person2.surname());
    }
}
