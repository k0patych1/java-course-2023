package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Project1Test {
    @Test
    @DisplayName("Empty word test")
    public void emptyWordTest() {
        String[] words = {""};

        HangmanGame hangmanGame = new HangmanGame();

        Dictionary dictionary = new Dictionary(words);
        assertThrows(IllegalArgumentException.class, () -> hangmanGame.run(dictionary));
    }

    @Test
    @DisplayName("Exceeding the maximum number of attempts")
    public void looseGameStateTest() throws Exception {
        String[] words = {"test"};

        Dictionary dictionary = new Dictionary(words);

        String attempts = """
                a
                b
                c
                d
                e
                f
                """;

        String expectedGame = """
                Hello and welcome to Hangman!
                The word: ****
                Guess a letter:\s
                Missed, mistake 1 of 5
                The word: ****
                Guess a letter:\s
                Missed, mistake 2 of 5
                The word: ****
                Guess a letter:\s
                Missed, mistake 3 of 5
                The word: ****
                Guess a letter:\s
                Missed, mistake 4 of 5
                The word: ****
                Guess a letter:\s
                Hit!
                The word: *e**
                Guess a letter:\s
                Missed, mistake 5 of 5
                The word: *e**
                You lost!
                The word was test
                """;

        String receivedOutGame = tapSystemOut(() -> {
            HangmanGame hangmanGame = new HangmanGame(attempts);
            hangmanGame.run(dictionary);
        });

        assertThat(receivedOutGame).isEqualTo(expectedGame);
    }

    @Test
    @DisplayName("The game is interrupted by pressing Ctrl + D")
    public void interruptedGameTest() throws Exception {
        String[] words = {"test"};

        Dictionary dictionary = new Dictionary(words);

        String attempts = """
                a
                e
                \u0004
                """;

        String expectedGame = """
                Hello and welcome to Hangman!
                The word: ****
                Guess a letter:\s
                Missed, mistake 1 of 5
                The word: ****
                Guess a letter:\s
                Hit!
                The word: *e**
                Guess a letter:\s
                """;

        String receivedOutGame = tapSystemOut(() -> {
            HangmanGame hangmanGame = new HangmanGame(attempts);
            hangmanGame.run(dictionary);
        });

        assertThat(receivedOutGame).isEqualTo(expectedGame);
    }

    @Test
    @DisplayName("A string of more than one character is processed correctly without changing state")
    public void stateTest() throws Exception {
        String[] words = {"test"};

        Dictionary dictionary = new Dictionary(words);

        String attempts = """
                a
                e
                de
                """;

        String expectedGame = """
                Hello and welcome to Hangman!
                The word: ****
                Guess a letter:\s
                Missed, mistake 1 of 5
                The word: ****
                Guess a letter:\s
                Hit!
                The word: *e**
                Guess a letter:\s
                You can only enter one letter per attempt
                """;

        String receivedOutGame = tapSystemOut(() -> {
            HangmanGame hangmanGame = new HangmanGame(attempts);
            hangmanGame.run(dictionary);
        });

        assertThat(receivedOutGame).isEqualTo(expectedGame);
    }
}
