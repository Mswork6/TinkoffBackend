package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class MazeSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.height();
        int width = maze.width();
        Coordinate[][] path = new Coordinate[height][width];
        boolean[][] visited = new boolean[height][width];
        int[][] distance = new int[height][width];
        Deque<Coordinate> deque = new ArrayDeque<>();

        if (!validateInputData(start, end, height, width)) {
            throw new IllegalArgumentException();
        }

        if (!bfs(maze, start, end, distance, visited, deque, path, height, width)) {
            return null;
        }
        return findPath(end, path);
    }

    private boolean validateInputData(Coordinate start, Coordinate end, int height, int width) {
        if (start.row() < 0 || start.row() >= height || start.col() < 0 || start.col() >= width) {
            return false;
        }
        return end.row() >= 0 && end.row() < height && end.col() >= 0 && end.col() < width;
    }

    private boolean validateCoordinate(int x, int y, int height, int width, boolean[][] visited) {
        return (x >= 0 && x < height && y >= 0 && y < width && !visited[x][y]);
    }

    @SuppressWarnings("ParameterNumber")
    private boolean bfs(
        Maze maze, Coordinate start, Coordinate end, int[][] distance,
        boolean[][] visited, Deque<Coordinate> deque, Coordinate[][] path, int height,
        int width
    ) {
        distance[start.row()][start.col()] = 0;
        visited[start.row()][start.col()] = true;
        deque.add(start);

        while (!deque.isEmpty()) {
            Coordinate current = deque.poll();
            int x = current.row();
            int y = current.col();
            if (validateCoordinate(x, y + 1, height, width, visited)
                && !maze.hasVerticalWall(x, y)) {
                distance[x][y + 1] = distance[x][y] + 1;
                path[x][y + 1] = current;
                visited[x][y + 1] = true;
                deque.add(new Coordinate(x, y + 1));
            }
            if (validateCoordinate(x, y - 1, height, width, visited)
                && !maze.hasVerticalWall(x, y - 1)) {
                distance[x][y - 1] = distance[x][y] + 1;
                path[x][y - 1] = current;
                visited[x][y - 1] = true;
                deque.add(new Coordinate(x, y - 1));
            }
            if (validateCoordinate(x + 1, y, height, width, visited)
                && !maze.hasHorizontalWall(x, y)) {
                distance[x + 1][y] = distance[x][y] + 1;
                path[x + 1][y] = current;
                visited[x + 1][y] = true;
                deque.add(new Coordinate(x + 1, y));
            }
            if (validateCoordinate(x - 1, y, height, width, visited)
                && !maze.hasHorizontalWall(x - 1, y)) {
                distance[x - 1][y] = distance[x][y] + 1;
                path[x - 1][y] = current;
                visited[x - 1][y] = true;
                deque.add(new Coordinate(x - 1, y));
            }
        }
        return distance[end.row()][end.col()] != 0;
    }

    List<Coordinate> findPath(Coordinate end, Coordinate[][] path) {
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
