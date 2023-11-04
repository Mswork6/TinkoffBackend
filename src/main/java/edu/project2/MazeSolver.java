package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class MazeSolver implements Solver {
    private int[][] distance;
    private Coordinate[][] path;
    private boolean[][] visited;
    private int height;
    private int width;
    private Deque<Coordinate> deque;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        height = maze.height();
        width = maze.width();
        if (!validateInputData(start, end)) {
            return null;
        }
        deque = new ArrayDeque<>();
        distance = new int[height][width];
        visited = new boolean[height][width];
        path = new Coordinate[height][width];

        initializeArrays();
        if (!dfs(maze, start, end)) {
            return null;
        }
        return findPath(end);
    }

    private void initializeArrays() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                distance[row][col] = Integer.MAX_VALUE;
                visited[row][col] = false;
                path[row][col] = null;
            }
        }
    }

    private boolean validateInputData(Coordinate start, Coordinate end) {
        if (start.row() < 0 || start.row() >= height || start.col() < 0 || start.col() >= width) {
            return false;
        }
        return end.row() >= 0 && end.row() < height && end.col() >= 0 && end.col() < width;
    }

    private boolean validateCoordinate(int x, int y) {
        return (x >= 0 && x < height && y >= 0 && y < width && !visited[x][y]);
    }

    private boolean dfs(Maze maze, Coordinate start, Coordinate end) {
        distance[start.row()][start.col()] = 0;
        visited[start.row()][start.col()] = true;
        deque.add(start);

        while (!deque.isEmpty()) {
            Coordinate current = deque.poll();
            int x = current.row();
            int y = current.col();
            if (validateCoordinate(x, y + 1) && !maze.hasVerticalWall(x, y)) {
                distance[x][y + 1] = distance[x][y] + 1;
                path[x][y + 1] = current;
                visited[x][y + 1] = true;
                deque.add(new Coordinate(x, y + 1));
            }
            if (validateCoordinate(x, y - 1) && !maze.hasVerticalWall(x, y - 1)) {
                distance[x][y - 1] = distance[x][y] + 1;
                path[x][y - 1] = current;
                visited[x][y - 1] = true;
                deque.add(new Coordinate(x, y - 1));
            }
            if (validateCoordinate(x + 1, y) && !maze.hasHorizontalWall(x, y)) {
                distance[x + 1][y] = distance[x][y] + 1;
                path[x + 1][y] = current;
                visited[x + 1][y] = true;
                deque.add(new Coordinate(x + 1, y));
            }
            if (validateCoordinate(x - 1, y) && !maze.hasHorizontalWall(x - 1, y)) {
                distance[x - 1][y] = distance[x][y] + 1;
                path[x - 1][y] = current;
                visited[x - 1][y] = true;
                deque.add(new Coordinate(x - 1, y));
            }
        }
        return distance[end.row()][end.col()] != Integer.MAX_VALUE;
    }

    List<Coordinate> findPath(Coordinate end) {
        Coordinate current = end;
        List<Coordinate> answer = new ArrayList<>();

        while (current != null) {
            answer.add(current);
            current = path[current.row()][current.col()];
        }
        Collections.reverse(answer);
        return answer;
    }
}
