package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class Task2 {

    public static final char OPENING_BRACKET = '(';
    public static final char CLOSING_BRACKET = ')';

    private Task2() {}

    private static String stackToString(Stack<Character> stack) {
        StringBuilder s = new StringBuilder();

        for (char ch : stack) {
            s.append(ch);
        }

        return s.toString();
    }

    public static List<String> clusterize(String s) {
        char[] brackets = s.toCharArray();

        Stack<Character> cluster = new Stack<>();
        cluster.push(brackets[0]);

        int numbOfOpeningBrackets = 1;
        int numbOfClosingBrackets = 0;
        int numOfBrackets = brackets.length;

        ArrayList<String> clusters = new ArrayList<>();

        for (int i = 1; i < numOfBrackets; ++i) {
            if (brackets[i] == OPENING_BRACKET) {
                ++numbOfOpeningBrackets;
            } else if (brackets[i] == CLOSING_BRACKET) {
                ++numbOfClosingBrackets;
            } else {
                throw new IllegalArgumentException("Invalid character detected");
            }

            cluster.push(brackets[i]);

            if (numbOfOpeningBrackets == numbOfClosingBrackets) {
                clusters.add(stackToString(cluster));
                cluster.clear();
            }
        }

        return clusters;
    }
}
