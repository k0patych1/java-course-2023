package edu.hw1;

public final class Task1 {
    @SuppressWarnings("MagicNumber")
    static int secondsInMinute = 60;

    private Task1() {}

    public static long countLengthOfVideo(String s) {
        String[] splited = s.split(":");

        if (splited.length != 2) {
            return -1;
        }

        long minutes;
        int seconds;

        try {
            minutes = Long.parseLong(splited[0]);
            seconds = Integer.parseInt(splited[1]);
        } catch (NumberFormatException exception) {
            return -1;
        }

        if (minutes < 0 || seconds >= secondsInMinute || seconds < 0) {
            return -1;
        }

        return minutes * secondsInMinute + seconds;
    }
}
