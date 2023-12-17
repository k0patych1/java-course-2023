package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public class ChillingGleb {
    private final int salary;

    private final int contestTink;

    private final int numOfOpravdanias;

    private final String surname;

    public ChillingGleb(@Max(0) int salary, @Max(0) int contestTink, @Min(100_000) int numOfOpravdanias, @NotNull String surname) {
        this.salary = salary;
        this.contestTink = contestTink;
        this.numOfOpravdanias = numOfOpravdanias;
        this.surname = surname;
    }

    public static ChillingGleb build() {
        return new ChillingGleb(0, 0, 100, "shapa");
    }

    public int getSalary() {
        return salary;
    }

    public int getContestTink() {
        return contestTink;
    }

    public int getNumOfOpravdanias() {
        return numOfOpravdanias;
    }

    public String getSurname() {
        return surname;
    }
}
