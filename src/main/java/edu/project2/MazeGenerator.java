package edu.project2;

import java.util.Random;

public class MazeGenerator implements Generator {
    private Random random;
    private final int height;
    private final int width;
    private boolean[][] verticalWalls;
    private boolean[][] horizontalWalls;
    private int[] string;
    private static final int EMPTY_VALUE = 0;
    private int counter = 1;

    @Override
    public Maze generate() {
        random = new Random();
        verticalWalls = new boolean[height][width];
        horizontalWalls = new boolean[height][width];
        string = new int[width];

        initializeWalls();
        fillString();

        for (int i = 0; i < height; i++) {
            makeUniqueSet();
            addVerticalWalls(i);
            addHorizontalWalls(i);
            checkHorizontalWalls(i);
            createNewString(i);
        }
        addLastLine();
        return new Maze(height, width, verticalWalls, horizontalWalls);

    }

    public MazeGenerator(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException();
        }
        this.height = height;
        this.width = width;
    }

    private void initializeWalls() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                verticalWalls[row][col] = false;
                horizontalWalls[row][col] = false;
            }
        }
    }

    private void fillString() {
        for (int i = 0; i < width; i++) {
            string[i] = EMPTY_VALUE;
        }
    }

    private void makeUniqueSet() {
        for (int i = 0; i < width; i++) {
            if (string[i] == EMPTY_VALUE) {
                string[i] = counter;
                counter++;
            }
        }
    }

    private void addVerticalWalls(int row) {
        for (int i = 0; i < width - 1; i++) {
            boolean chance = random.nextBoolean();
            if (chance || string[i] == string[i + 1]) {
                verticalWalls[row][i] = true;
            } else {
                mergeInOneSet(i, string[i]);
            }
        }
        verticalWalls[row][width - 1] = true;

    }

    private void mergeInOneSet(int index, int value) {
        int set = string[index + 1];
        for (int j = 0; j < width; j++) {
            if (string[j] == set) {
                string[j] = value;
            }
        }
    }

    private void addHorizontalWalls(int row) {
        for (int i = 0; i < width; i++) {
            boolean chance = random.nextBoolean();
            if (countUniqueSet(string[i]) != 1 && chance) {
                horizontalWalls[row][i] = true;
            }
        }
    }

    private int countUniqueSet(int value) {
        int count = 0;
        for (int i = 0; i < width; i++) {
            if (string[i] == value) {
                count++;
            }
        }
        return count;
    }

    void checkHorizontalWalls(int row) {
        for (int i = 0; i < width; i++) {
            if (countHorizontalWalls(string[i], row) == 0) {
                horizontalWalls[row][i] = false;
            }
        }
    }

    int countHorizontalWalls(int element, int row) {
        int countHorizontalWalls = 0;
        for (int i = 0; i < width; i++) {
            if (string[i] == element && !horizontalWalls[row][i]) {
                countHorizontalWalls++;
            }
        }
        return countHorizontalWalls;
    }

    void createNewString(int row) {
        for (int i = 0; i < width; i++) {
            if (horizontalWalls[row][i]) {
                string[i] = EMPTY_VALUE;
            }
        }
    }

    void addLastLine() {
        makeUniqueSet();
        addHorizontalWalls(height - 1);
        checkLastLine();
    }

    void checkLastLine() {
        for (int i = 0; i < width - 1; i++) {
            if (string[i] != string[i + 1]) {
                verticalWalls[height - 1][i] = false;
                mergeInOneSet(i, string[i]);
            }
            horizontalWalls[height - 1][i] = true;
        }
        horizontalWalls[height - 1][width - 1] = true;
    }

}
