package edu.project1;

import org.jetbrains.annotations.NotNull;

public class HangmanGame {
    private static final char END_GAME = '\u0004';
    private static final char HIDDEN_LETTER = '*';

    private final ConsoleHangmanManager consoleHangmanManager;

    public HangmanGame() {
        consoleHangmanManager = new ConsoleHangmanManager();
    }

    public HangmanGame(String attempts) {
        consoleHangmanManager = new ConsoleHangmanManager(attempts);
    }

    @SuppressWarnings("ReturnCount")
    public void run(@NotNull IDictionary dictionary) throws EnumConstantNotPresentException {
        char[] hiddenWord = dictionary.randomWord().toCharArray();

        Session session = new Session(hiddenWord);
        consoleHangmanManager.showStartOfGame(session.guessedPartOfWord);

        while (consoleHangmanManager.isGetLetter()) {
            Character letter = consoleHangmanManager.letter();

            if (letter == null) {
                continue;
            }

            if (letter == END_GAME) {
                break;
            }

            GuessResult guessResult = guessResult(session, letter);

            switch (guessResult) {
                case SUCCESS_ATTEMPT -> consoleHangmanManager.showSuccessAttempt(session.guessedPartOfWord);
                case WRONG_ATTEMPT -> consoleHangmanManager.showWrongAttempt(
                    session.guessedPartOfWord, session.numOfAttempts, session.maxAttempts);
                case LOOSE -> {
                    consoleHangmanManager.showLoose(
                        session.guessedPartOfWord, session.hiddenWord, session.numOfAttempts, session.maxAttempts);
                    return;
                }
                case WIN -> {
                    consoleHangmanManager.showWin(session.guessedPartOfWord);
                    return;
                } case LETTER_ALREADY_CHECKED -> consoleHangmanManager.showRetry(letter);

                default -> throw new EnumConstantNotPresentException(guessResult.getClass(), guessResult.name());
            }

            consoleHangmanManager.requestLetter();
        }
    }

    @SuppressWarnings("ReturnCount")
    private static GuessResult guessResult(@NotNull Session session,  char letter) {
        if (session.attempts.contains(letter)) {
            return GuessResult.LETTER_ALREADY_CHECKED;
        } else {
            session.attempts.add(letter);
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
}
