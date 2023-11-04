package edu.project2;

public final class Maze {

    private final int height;
    private final int width;
    private final boolean[][] verticalWalls;
    private final boolean[][] horizontalWalls;

    public Maze(int height, int width, boolean[][] vertArr, boolean[][] horizArr) {
        this.height = height;
        this.width = width;
        this.verticalWalls = vertArr;
        this.horizontalWalls = horizArr;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public boolean hasVerticalWall(int row, int col) {
        if ((row >= 0 && row < width) && (col >= 0 && col < height)) {
            return verticalWalls[row][col];
        }
        return false;
    }

    public boolean hasHorizontalWall(int row, int col) {
        if ((row >= 0 && row < width) && (col >= 0 && col < height)) {
            return horizontalWalls[row][col];
        }
        return false;
    }

}
