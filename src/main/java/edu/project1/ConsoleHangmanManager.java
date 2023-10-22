package edu.project1;

import java.util.Scanner;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleHangmanManager {
    private final Scanner scanner;

    @Nullable
    private Character curLetter;

    ConsoleHangmanManager() {
        scanner = new Scanner(System.in);
        requestLetter();
    }

    ConsoleHangmanManager(String attempts) {
        scanner = new Scanner(attempts);
        requestLetter();
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

    public void showSuccessAttempt(char[] outWord) {
        System.out.println("Hit!");
        showWord(outWord);
    }

    public void showWrongAttempt(char[] outWord, int attempts, int maxAttempts) {
        System.out.print("Missed, mistake ");
        System.out.print(attempts);
        System.out.print(" of ");
        System.out.println(maxAttempts);
        showWord(outWord);
    }

    public void showLoose(char[] outWord, int attempts, int maxAttempts) {
        showWrongAttempt(outWord, attempts, maxAttempts);
        System.out.println("You lost!");
    }

    public void showWin(char[] outWord) {
        showSuccessAttempt(outWord);
        System.out.println("You won!");
    }

    public void requestLetter() {
        System.out.println("Guess a letter: ");
    }

    private void showWord(char[] outWord) {
        System.out.print("The word: ");
        System.out.println(outWord);
    }
}
