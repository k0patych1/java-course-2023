package edu.hw8.task3;

public final class PasswordGenerator {
    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final static int ALPHABET_LENGTH = 62;

    private PasswordGenerator() {}

    public static String generate(int n, int passwordLength) {
        StringBuilder password = new StringBuilder();
        while (n > 0) {
            password.insert(0, ALPHABET.charAt(n % ALPHABET_LENGTH));
            n /= ALPHABET_LENGTH;
        }

        while (password.length() < passwordLength) {
            password.insert(0, ALPHABET.charAt(0));
        }

        return password.toString();
    }
}
