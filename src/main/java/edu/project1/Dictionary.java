package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Dictionary implements IDictionary {
    private String[] words = new String[] {"capybara", "dopsa", "cadillac"};

    public Dictionary() {}

    public Dictionary(String[] words) {
        this.words = words;
    }

    @Override
    public @NotNull String randomWord() {
        return words[new Random().nextInt(words.length)];
    }
}

