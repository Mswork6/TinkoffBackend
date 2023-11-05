package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Project2Test {
    @Test
    @DisplayName("Проверка правильного нахождения пути в лабиринте")
    void checkRightPath() {
        // given
        boolean[][] verticalWalls = {
            {true, true, false, false, true},
            {false, false, true, true, true},
            {false, true, true, false, true},
            {false, true, true, true, true},
            {false, false, false, false, false},
        };

        boolean[][] horizontalWalls = {
            {false, false, false, false, true},
            {true, false, false, false, false},
            {true, false, false, true, true},
            {true, true, false, false, false},
            {true, true, true, true, true},
        };

        int height = 5;
        int width = 5;

        List<Coordinate> answer = new ArrayList<>();
        answer.add(new Coordinate(0, 0));
        answer.add(new Coordinate(1, 0));
        answer.add(new Coordinate(1, 1));
        answer.add(new Coordinate(1, 2));
        answer.add(new Coordinate(2, 2));
        answer.add(new Coordinate(3, 2));
        answer.add(new Coordinate(4, 2));
        answer.add(new Coordinate(4, 3));
        answer.add(new Coordinate(3, 3));

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // when
        Maze maze = new Maze(height, width, verticalWalls, horizontalWalls);
        MazeSolver solver = new MazeSolver();
        List<Coordinate> result = solver.solve(maze, start, end);

        // then
        assertEquals(result, answer);
    }

    @Test
    @DisplayName("Проверка правильной отрисовки лабиринта")
    void checkRightMaze() {
        // given
        boolean[][] verticalWalls = {
            {false, true, true, false, true},
            {true, false, false, true, true},
            {false, false, true, true, true},
            {false, false, true, false, true},
            {false, false, true, false, true},
        };

        boolean[][] horizontalWalls = {
            {true, false, false, true, true},
            {true, true, false, false, false},
            {true, false, true, false, false},
            {true, false, false, false, false},
            {true, true, true, true, true},
        };

        int height = 5;
        int width = 5;

        String answer =
            """
                _____________________
                |_■__ ■ |   |_______|
                |___|_■__ ■   ■ |   |
                |____    ___| ■ |   |
                |____       | ■     |
                |___________|_______|
                """;

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // when
        Maze maze = new Maze(height, width, verticalWalls, horizontalWalls);
        MazeSolver solver = new MazeSolver();
        List<Coordinate> path = solver.solve(maze, start, end);
        MazeRenderer renderer = new MazeRenderer();
        String result = renderer.render(maze, path);

        // then
        assertEquals(result, answer);
    }

    @Test
    @DisplayName("Проверка корректных данных при поиске пути")
    void checkIncorrectInputGenerate() {
        // given

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new MazeGenerator(0, 6));
    }

    @Test
    @DisplayName("Проверка корректных данных при генерации лабиринта")
    void checkIncorrectInputPath() {
        // given

        // when
        MazeGenerator generator = new MazeGenerator(5, 5);
        Maze maze = generator.generate();
        Coordinate start = new Coordinate(-1, 6);
        Coordinate end = new Coordinate(5, 5);
        MazeSolver solver = new MazeSolver();

        // then
        assertThrows(IllegalArgumentException.class, () -> solver.solve(maze, start, end));
    }

}
