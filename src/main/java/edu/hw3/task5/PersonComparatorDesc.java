package edu.hw3.task5;

public class PersonComparatorDesc extends PersonComparatorAsc {
    @Override
    public int compare(Person person1, Person person2) {
        return super.compare(person2, person1);
    }
}
