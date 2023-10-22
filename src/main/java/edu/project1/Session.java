package edu.project1;

import java.util.Arrays;

public class Session {
    public char[] word;

    int lengthOfWord;

    public static final int MAX_ATTEMPTS = 5;

    public int attempts;

    public char[] outWord;

    public Session(char[] word) {
        lengthOfWord = word.length;

        if (lengthOfWord == 0) {
            throw new IllegalArgumentException("Word's length can't be zero");
        }

        this.word = word;
        this.attempts = 0;
        outWord = new char[word.length];
        Arrays.fill(outWord, '*');
    }
}
