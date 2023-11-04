package edu.project2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MazeGenerator gen = new MazeGenerator(10, 10);
        Maze test = gen.generate();
        MazeRenderer render = new MazeRenderer();

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(8, 5);
        MazeSolver solver = new MazeSolver();
        List<Coordinate> path = solver.solve(test, start, end);

        String res = render.render(test, path);
        System.out.println(res);

    }
}
