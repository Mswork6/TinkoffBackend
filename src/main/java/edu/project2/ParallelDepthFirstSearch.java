package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelDepthFirstSearch implements Solver {
    private final ReentrantLock lock = new ReentrantLock();
    private final AtomicBoolean solutionFound = new AtomicBoolean(false);

    @Override
    @SuppressWarnings("MagicNumber")
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.height();
        int width = maze.width();
        Coordinate[][] path = new Coordinate[height][width];
        boolean[][] visited = new boolean[height][width];
        Deque<Coordinate> stack = new ArrayDeque<>();

        if (!validateInputData(start, end, height, width)) {
            throw new IllegalArgumentException();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        try {
            stack.push(start);

            while (!stack.isEmpty() && !solutionFound.get()) {
                Coordinate current = stack.pop();
                int x = current.row();
                int y = current.col();

                if (!visited[x][y]) {
                    visited[x][y] = true;

                    if (x == end.row() && y == end.col()) {
                        solutionFound.set(true);
                        break;
                    }

                    List<CompletableFuture<Void>> futures = new ArrayList<>();

                    futures.add(CompletableFuture.runAsync(() -> checkAndProcessNeighbor(x, y + 1, x, y, maze,
                        path, visited, stack
                    ), executorService));
                    futures.add(CompletableFuture.runAsync(() -> checkAndProcessNeighbor(x, y - 1, x, y, maze,
                        path, visited, stack
                    ), executorService));
                    futures.add(CompletableFuture.runAsync(() -> checkAndProcessNeighbor(x + 1, y, x, y, maze,
                        path, visited, stack
                    ), executorService));
                    futures.add(CompletableFuture.runAsync(() -> checkAndProcessNeighbor(x - 1, y, x, y, maze,
                        path, visited, stack
                    ), executorService));

                    CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
                    allOf.get(); // Ожидаем завершения всех задач текущей итерации
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        } finally {
            executorService.shutdown();
        }

        return (visited[end.row()][end.col()]) ? reconstructPath(start, path) : null;
    }

    @SuppressWarnings("ParameterNumber")
    private void checkAndProcessNeighbor(
        int newX, int newY, int startX, int startY,
        Maze maze, Coordinate[][] path,
        boolean[][] visited, Deque<Coordinate> stack
    ) {
        if (solutionFound.get()) {
            return;
        }

        lock.lock();
        try {

            if (validateCoordinate(newX, newY, maze.height(), maze.width(), visited)) {
                if (newY == startY + 1 && !maze.hasVerticalWall(startX, startY)) {
                    path[newX][newY] = new Coordinate(startX, startY);
                    stack.push(new Coordinate(newX, newY));
                }
                if (newY == startY - 1 && !maze.hasVerticalWall(startX, startY - 1)) {
                    path[newX][newY] = new Coordinate(startX, startY);
                    stack.push(new Coordinate(newX, newY));
                }
                if (newX == startX + 1 && !maze.hasHorizontalWall(startX, startY)) {
                    path[newX][newY] = new Coordinate(startX, startY);
                    stack.push(new Coordinate(newX, newY));
                }
                if (newX == startX - 1 && !maze.hasHorizontalWall(startX - 1, startY)) {
                    path[newX][newY] = new Coordinate(startX, startY);
                    stack.push(new Coordinate(newX, newY));
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private boolean validateCoordinate(int x, int y, int height, int width, boolean[][] visited) {
        return (x >= 0 && x < height && y >= 0 && y < width && !visited[x][y]);
    }

    private boolean validateInputData(Coordinate start, Coordinate end, int height, int width) {
        if (start.row() < 0 || start.row() >= height || start.col() < 0 || start.col() >= width) {
            return false;
        }
        return end.row() >= 0 && end.row() < height && end.col() >= 0 && end.col() < width;
    }

    private List<Coordinate> reconstructPath(Coordinate end, Coordinate[][] path) {
        List<Coordinate> result = new ArrayList<>();
        Coordinate current = end;

        while (current != null) {
            result.add(current);
            current = path[current.row()][current.col()];
        }

        Collections.reverse(result);
        return result;
    }
}
