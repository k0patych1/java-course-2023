package edu.project1;

import java.util.Scanner;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleHangmanManager implements AutoCloseable {
    private final Scanner scanner;

    @Nullable
    private Character curLetter;

    public ConsoleHangmanManager() {
        scanner = new Scanner(System.in);
    }

    public ConsoleHangmanManager(String attempts) {
        scanner = new Scanner(attempts);
    }

    public boolean isGetLetter() {
        if (!scanner.hasNext()) {
            return false;
        }

        String input = scanner.next();

        if (input.length() != 1) {
            System.out.println("You can only enter one letter per attempt");
            curLetter = null;

            return true;
        }

        curLetter = input.charAt(0);

        return true;
    }

    public Character letter() {
        return curLetter;
    }

    public void showStartOfGame(char[] outword) {
        System.out.println("Hello and welcome to Hangman!");
        showGuessedPartOfWord(outword);
        requestLetter();
    }

    public void showSuccessAttempt(char[] outWord) {
        System.out.println("Hit!");
        showGuessedPartOfWord(outWord);
    }

    public void showWrongAttempt(char[] outWord, int attempts, int maxAttempts) {
        System.out.println("Missed, mistake " + attempts + " of " + maxAttempts);
        showGuessedPartOfWord(outWord);
    }

    public void showLoose(char[] outWord, char[] hiddenWord, int attempts, int maxAttempts) {
        showWrongAttempt(outWord, attempts, maxAttempts);
        System.out.println("You lost!");
        showHiddenWord(hiddenWord);
    }

    public void showWin(char[] outWord) {
        showSuccessAttempt(outWord);
        System.out.println("You won!");
    }

    public void showRetry(char letter) {
        System.out.println("Letter '" + letter + "' has already been checked\n"
            + "Try another letter");
    }

    private void showGuessedPartOfWord(char[] outWord) {
        System.out.print("The word: ");
        System.out.println(outWord);
    }

    private void showHiddenWord(char[] hiddenWord) {
        System.out.print("The word was ");
        System.out.println(hiddenWord);
    }

    public void requestLetter() {
        System.out.println("Guess a letter: ");
    }

    @Override
    public void close() {
        scanner.close();
    }
}
