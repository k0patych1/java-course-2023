package edu.hw3.task7;

import java.util.Comparator;
import org.jetbrains.annotations.Nullable;


public class NullComparator implements Comparator<java.lang.String> {
    @Override
    public int compare(@Nullable String string1, @Nullable String string2) {
        if (string1 == null && string2 == null) {
            return 0;
        }
        if (string1 == null) {
            return 1;
        }
        if (string2 == null) {
            return -1;
        }

        return string1.compareTo(string2);
    }
}
