package edu.hw1;

public class Task8 {
    private final static int MAX_SIZE = 8;

    private final static int[] X_MOVES = {-2, -2, -1, 1, 2, 2, 1, -1};
    private final static int[] Y_MOVES = {-1, 1, 2, 2, 1, -1, -2, -2};

    private Task8() {
    }

    private static boolean validateArray(int[][] array) {
        if (array.length == MAX_SIZE) {
            for (int[] ints : array) {
                if (ints.length != MAX_SIZE) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean checkKnight(int x, int y, int[][] array) {
        for (int i = 0; i < X_MOVES.length; i++) {
            int newX = x + X_MOVES[i];
            int newY = y + Y_MOVES[i];
            if ((newX >= 0 && newX < MAX_SIZE) && (newY >= 0 && newY < MAX_SIZE)
                && (array[newX][newY] == 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean knightBoardCapture(int[][] array) {
        if (!validateArray(array)) {
            return false;
        }

        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                if (array[i][j] != 0 && array[i][j] != 1) {
                    return false;
                }
                if (array[i][j] == 1) {
                    if (!checkKnight(i, j, array)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
