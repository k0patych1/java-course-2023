package edu.project1;

import java.util.Arrays;
import java.util.HashSet;

public class Session {
    public final int maxAttempts = 5;

    public char[] hiddenWord;

    public int lengthOfWord;

    public int numOfAttempts;

    public HashSet<Character> attempts;

    public char[] guessedPartOfWord;

    public Session(char[] word) throws IllegalArgumentException {
        lengthOfWord = word.length;

        if (lengthOfWord == 0) {
            throw new IllegalArgumentException("Word's length can't be zero");
        }

        this.hiddenWord = word;
        this.numOfAttempts = 0;
        attempts = new HashSet<>();
        guessedPartOfWord = new char[word.length];
        Arrays.fill(guessedPartOfWord, '*');
    }
}
