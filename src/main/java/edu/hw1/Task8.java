package edu.hw1;

public final class Task8 {
    private Task8() {}

    private static final int BOARD_SIZE = 8;
    private static final String BOARD_SIZE_EXCEPTION_DESCRIPTION = "The board must be BOARD_SIZE*BOARD_SIZE";

    private static boolean isCorrectArrangement(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) throws IllegalArgumentException {
        if (board.length != BOARD_SIZE) {
            throw new IllegalArgumentException(BOARD_SIZE_EXCEPTION_DESCRIPTION);
        }

        int[][] possibleArrangements = {
            {2, 1},
            {2, -1},
            {-2, 1},
            {-2, -1},
            {1, 2},
            {1, -2},
            {-1, 2},
            {-1, -2}
        };

        for (int x = 0; x < BOARD_SIZE; ++x) {
            if (board[x].length != BOARD_SIZE) {
                throw new IllegalArgumentException(BOARD_SIZE_EXCEPTION_DESCRIPTION);
            }

            for (int y = 0; y < BOARD_SIZE; ++y) {
                if (board[x][y] != 1) {
                    continue;
                }

                for (var pair : possibleArrangements) {
                    int xOffset = pair[0];
                    int yOffset = pair[1];

                    if (isCorrectArrangement(x - xOffset, y - yOffset)
                        && board[x - xOffset][y - yOffset] == 1) {
                        return  false;
                    }
                }
            }
        }

        return true;
    }
}
