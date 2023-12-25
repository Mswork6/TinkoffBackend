package edu.project2;

import java.util.Random;

/**
 * Класс генерации лабиринта, использующий для этого
 * алгоритм Эллера.
 */
public class MazeGenerator implements Generator {
    private static final int EMPTY_VALUE = 0;

    private final Random random = new Random();
    private final int height;
    private final int width;
    /* Массив, отражающий наличие правой вертикальной стены
    у заданной ячейки */
    private boolean[][] verticalWalls;
    /* Массив, отражающий наличие нижней горизонтальной стены
    у заданной ячейки */
    private boolean[][] horizontalWalls;

    public MazeGenerator(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException();
        }
        this.height = height;
        this.width = width;
    }

    @Override
    public Maze generate() {
        verticalWalls = new boolean[height][width];
        horizontalWalls = new boolean[height][width];
        int[] newRow = new int[width];
        int counter = 1;

        /* В данном цикле происходит полная генерация одной сточки лабиринта,
         * включая генерацию самой строки, добавление вертикальных и горизонтальных стен,
         * а так же генерацию новой строки для следующей итерации */
        for (int i = 0; i < height; i++) {
            counter = makeUniqueSet(newRow, counter);
            addVerticalWalls(i, newRow);
            checkAndFixHorizontalWalls(i, newRow);
            checkHorizontalWalls(i, newRow);
            newRow = createNewString(i, newRow);
        }
        addLastLine(newRow, counter);
        return new Maze(height, width, verticalWalls, horizontalWalls);

    }

    /* Присваиваем ячейкам уникальное множество для дальнейшего
     построения стен в лабиринте */
    private int makeUniqueSet(int[] line, int counter) {
        int newCounter = counter;
        for (int i = 0; i < width; i++) {
            if (line[i] == EMPTY_VALUE) {
                line[i] = newCounter;
                newCounter += 1;
            }
        }
        return newCounter;
    }
    /* Здесь теперь нужно возвращать значения счётчика,
    * чтобы он корректно обновлялся */

    private void addVerticalWalls(int row, int[] line) {
        for (int i = 0; i < width - 1; i++) {
            boolean chance = random.nextBoolean();
            if (chance || line[i] == line[i + 1]) {
                verticalWalls[row][i] = true;
            } else {
                mergeInOneSet(i, line);
            }
        }
        /* Добавляем правую стену для последней ячейки,
        так как это конец строки */
        verticalWalls[row][width - 1] = true;

    }

    private void mergeInOneSet(int index, int[] line) {
        int oldSet = line[index + 1];
        for (int j = 0; j < width; j++) {
            if (line[j] == oldSet) {
                line[j] = line[index];
            }
        }
    }

    private void checkAndFixHorizontalWalls(int row, int[] line) {
        for (int i = 0; i < width; i++) {
            boolean chance = random.nextBoolean();
            if (countUniqueSet(line[i], line) != 1 && chance) {
                horizontalWalls[row][i] = true;
            }
        }
    }

    private int countUniqueSet(int value, int[] line) {
        int count = 0;
        for (int i = 0; i < width; i++) {
            if (line[i] == value) {
                count++;
            }
        }
        return count;
    }

    void checkHorizontalWalls(int row, int[] line) {
        for (int i = 0; i < width; i++) {
            if (countCellsWithoutHorizontalWalls(line[i], row, line) == 0) {
                horizontalWalls[row][i] = false;
            }
        }
    }

    int countCellsWithoutHorizontalWalls(int element, int row, int[] line) {
        int counter = 0;
        for (int i = 0; i < width; i++) {
            if (line[i] == element && !horizontalWalls[row][i]) {
                counter++;
            }
        }
        return counter;
    }

    int[] createNewString(int row, int[] line) {
        int[] newLine = line.clone();
        for (int i = 0; i < width; i++) {
            if (horizontalWalls[row][i]) {
                newLine[i] = EMPTY_VALUE;
            }
        }
        return newLine;
    }

    void addLastLine(int[] row, int counter) {
        makeUniqueSet(row, counter);
        checkAndFixHorizontalWalls(height - 1, row);
        checkAndFixLastLine(row);
    }

    void checkAndFixLastLine(int[] row) {
        for (int i = 0; i < width - 1; i++) {
            if (row[i] != row[i + 1]) {
                verticalWalls[height - 1][i] = false;
                mergeInOneSet(i, row);
            }
            horizontalWalls[height - 1][i] = true;
        }
        horizontalWalls[height - 1][width - 1] = true;
    }

}
