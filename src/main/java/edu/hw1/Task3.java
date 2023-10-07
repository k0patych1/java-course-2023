package edu.hw1;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.OptionalInt;

public final class Task3 {
    private Task3() {}

    public static boolean isNestable(int[] nestedArr, int[] arr) {
        OptionalInt minOfNestedArr = Arrays.stream(nestedArr).min();
        OptionalInt minOfArr = Arrays.stream(arr).min();
        OptionalInt maxOfNestedArr = Arrays.stream(nestedArr).max();
        OptionalInt maxOfArr = Arrays.stream(arr).max();

        try {
            boolean isNestable = minOfNestedArr.getAsInt() > minOfArr.getAsInt()
                && maxOfNestedArr.getAsInt() < maxOfArr.getAsInt();

            return isNestable;
        } catch (NoSuchElementException exception) {
            return true;
        }
    }
}
