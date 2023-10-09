package edu.hw1;

public final class Task8 {
    private Task8() {}

    private static final String BOARD_SIZE_EXCEPTION_DESCRIPTION = "The number must be positive";

    @SuppressWarnings("MagicNumber")
    private static boolean isCorrectArrangement(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) throws IllegalArgumentException {
        if (board.length != 8) {
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

        for (int x = 0; x < 8; ++x) {
            if (board[x].length != 8) {
                throw new IllegalArgumentException(BOARD_SIZE_EXCEPTION_DESCRIPTION);
            }

            for (int y = 0; y < 8; ++y) {
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
