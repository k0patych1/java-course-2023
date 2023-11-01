package edu.project1;

import org.jetbrains.annotations.NotNull;

public class HangmanGame implements AutoCloseable {
    private static final char FORCE_END_GAME = '\u0004';
    private static final char HIDDEN_LETTER = '*';

    private final ConsoleHangmanManager consoleHangmanManager;

    public HangmanGame() {
        consoleHangmanManager = new ConsoleHangmanManager();
    }

    public HangmanGame(String attempts) {
        consoleHangmanManager = new ConsoleHangmanManager(attempts);
    }

    public void run(@NotNull IDictionary dictionary) throws EnumConstantNotPresentException {
        char[] hiddenWord = dictionary.randomWord().toCharArray();

        Session session = new Session(hiddenWord);
        consoleHangmanManager.showStartOfGame(session.guessedPartOfWord);

        while (consoleHangmanManager.isGetLetter()) {
            Character letter = consoleHangmanManager.letter();

            if (letter == null) {
                continue;
            }

            GuessResult guessResult = guessResult(session, letter);

            showToUser(guessResult, session, letter);

            if (isGameEnded(guessResult)) {
                return;
            }

            consoleHangmanManager.requestLetter();
        }
    }

    @SuppressWarnings("ReturnCount")
    private static GuessResult guessResult(@NotNull Session session,  char letter) {
        if (letter == FORCE_END_GAME) {
           return GuessResult.FORCE_END_GAME;
        }

        if (!session.attempts.add(letter)) {
            return GuessResult.LETTER_ALREADY_CHECKED;
        }

        boolean isSuccessAttempt = false;
        boolean isWon = true;

        int lengthOfWord = session.hiddenWord.length;

        for (int i = 0; i < lengthOfWord; ++i) {
            if (session.hiddenWord[i] == letter) {
                isSuccessAttempt = true;
                session.guessedPartOfWord[i] = letter;
            }

            if (session.guessedPartOfWord[i] == HIDDEN_LETTER) {
                isWon = false;
            }
        }

        if (isSuccessAttempt) {
            if (isWon) {
                return GuessResult.WIN;
            }

            return GuessResult.SUCCESS_ATTEMPT;
        }

        ++session.numOfAttempts;

        if (session.numOfAttempts == session.maxAttempts) {
            return GuessResult.LOOSE;
        }

        return GuessResult.WRONG_ATTEMPT;
    }

    private boolean isGameEnded(GuessResult guessResult) {
        switch (guessResult) {
            case LOOSE, WIN, FORCE_END_GAME -> {
                return true;
            }
            case SUCCESS_ATTEMPT, WRONG_ATTEMPT, LETTER_ALREADY_CHECKED -> {
                return false;
            }
            default -> throw new EnumConstantNotPresentException(guessResult.getClass(), guessResult.name());
        }
    }

    private void showToUser(GuessResult guessResult, Session session, char letter)
        throws EnumConstantNotPresentException {
        switch (guessResult) {
            case SUCCESS_ATTEMPT -> consoleHangmanManager.showSuccessAttempt(session.guessedPartOfWord);
            case WRONG_ATTEMPT -> consoleHangmanManager.showWrongAttempt(
                session.guessedPartOfWord, session.numOfAttempts, session.maxAttempts);
            case LOOSE -> consoleHangmanManager.showLoose(
                    session.guessedPartOfWord, session.hiddenWord, session.numOfAttempts, session.maxAttempts);
            case WIN -> consoleHangmanManager.showWin(session.guessedPartOfWord);
            case LETTER_ALREADY_CHECKED -> consoleHangmanManager.showRetry(letter);
            case FORCE_END_GAME -> consoleHangmanManager.showHiddenWord(session.hiddenWord);

            default -> throw new EnumConstantNotPresentException(guessResult.getClass(), guessResult.name());
        }
    }

    @Override
    public void close() {
        consoleHangmanManager.close();
    }
}
