package edu.project1;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();

        HangmanGame hangmanGame = new HangmanGame();

        hangmanGame.run(dictionary);
    }
}
