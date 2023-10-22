package edu.project1;

import org.jetbrains.annotations.NotNull;

public class HangmanGame {
    private static final char END_GAME = '\u0004';

    private final ConsoleHangmanManager consoleHangmanManager;

    HangmanGame() {
        consoleHangmanManager = new ConsoleHangmanManager();
    }

    HangmanGame(String attempts) {
        consoleHangmanManager = new ConsoleHangmanManager(attempts);
    }

    public void run(@NotNull IDictionary dictionary) throws EnumConstantNotPresentException {
        Session session = new Session(dictionary.randomWord().toCharArray());

        while (consoleHangmanManager.isGetLetter()) {
            Character letter = consoleHangmanManager.letter();

            if (letter == null) {
                continue;
            }

            if (letter == END_GAME) {
                break;
            }

            GuessResult guessResult = guessResult(session, letter);

            boolean isGameEnded = false;

            switch (guessResult) {
                case SuccessAttempt -> consoleHangmanManager.showSuccessAttempt(session.outWord);
                case WrongAttempt -> consoleHangmanManager.showWrongAttempt(
                    session.outWord, session.attempts, Session.MAX_ATTEMPTS);
                case Loose -> {
                    consoleHangmanManager.showLoose(
                        session.outWord, session.attempts, Session.MAX_ATTEMPTS);
                    isGameEnded = true;
                }
                case WIN -> {
                    consoleHangmanManager.showWin(session.outWord);
                    isGameEnded = true;
                }

                default -> throw new EnumConstantNotPresentException(guessResult.getClass(), guessResult.name());
            }

            if (isGameEnded) {
                return;
            }

            consoleHangmanManager.requestLetter();
        }
    }

    private static GuessResult guessResult(@NotNull Session session,  char letter) {
        boolean isSuccessAttempt = false;
        boolean isWon = true;

        int lengthOfWord = session.word.length;

        for (int i = 0; i < lengthOfWord; ++i) {
            if (session.word[i] == letter) {
                isSuccessAttempt = true;
                session.outWord[i] = letter;
            }

            if (session.outWord[i] == '*') {
                isWon = false;
            }
        }

        if (isSuccessAttempt) {
            if (isWon) {
                return GuessResult.WIN;
            }

            return GuessResult.SuccessAttempt;
        }

        ++session.attempts;

        if (session.attempts == Session.MAX_ATTEMPTS) {
            return GuessResult.Loose;
        }

        return GuessResult.WrongAttempt;
    }
}
