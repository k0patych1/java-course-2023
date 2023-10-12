package edu.hw1;

public final class Task1 {
    private static final int SECOND_IN_MINUTE = 60;

    private Task1() {}

    public static long countLengthOfVideo(String s) {
        String[] splited = s.split(":");

        if (splited.length != 2) {
            return -1;
        }

        int minutes;
        int seconds;

        try {
            minutes = Integer.parseInt(splited[0]);
            seconds = Integer.parseInt(splited[1]);
        } catch (NumberFormatException exception) {
            return -1;
        }

        if (minutes < 0 || seconds >= SECOND_IN_MINUTE || seconds < 0) {
            return -1;
        }

        return (long) minutes * SECOND_IN_MINUTE + seconds;
    }
}
