package edu.hw1;

public final class Task1 {
    private static final int secondsInMinute = 60;

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

        if (minutes < 0 || seconds >= secondsInMinute || seconds < 0) {
            return -1;
        }

        return (long) minutes * secondsInMinute + seconds;
    }
}
