package edu.project2;

import java.util.List;

public class MazeRenderer implements Renderer {
    private static final int CELL_SIZE = 4;

    @Override
    public String render(Maze maze) {
        StringBuilder sb = new StringBuilder();
        sb.append("_".repeat(CELL_SIZE * maze.width() + 1));
        sb.append('\n');

        for (int row = 0; row < maze.height(); row++) {
            StringBuilder arrayRow = new StringBuilder("|");
            for (int col = 0; col < maze.width(); col++) {
                boolean verticalWall = maze.hasVerticalWall(row, col);
                boolean horizontalWall = maze.hasHorizontalWall(row, col);
                arrayRow.append(renderNoPath(verticalWall, horizontalWall));
            }
            sb.append(arrayRow);
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        if (path == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_".repeat(CELL_SIZE * maze.width() + 1));
        sb.append('\n');

        for (int row = 0; row < maze.height(); row++) {
            StringBuilder arrayRow = new StringBuilder("|");
            for (int col = 0; col < maze.width(); col++) {
                boolean verticalWall = maze.hasVerticalWall(row, col);
                boolean horizontalWall = maze.hasHorizontalWall(row, col);
                if (path.contains(Coordinate.of(row, col))) {
                    arrayRow.append(renderWithPath(verticalWall, horizontalWall));
                } else {
                    arrayRow.append(renderNoPath(verticalWall, horizontalWall));
                }
            }
            sb.append(arrayRow);
            sb.append('\n');
        }
        return sb.toString();
    }

    private String renderNoPath(boolean verticalWall, boolean horizontalWall) {
        if (verticalWall && horizontalWall) {
            return ("___|");
        } else if (verticalWall) {
            return ("   |");
        } else if (horizontalWall) {
            return ("____");
        }
        return ("    ");
    }

    private String renderWithPath(boolean verticalWall, boolean horizontalWall) {
        if (verticalWall && horizontalWall) {
            return ("_■_|");
        } else if (verticalWall) {
            return (" ■ |");
        } else if (horizontalWall) {
            return ("_■__");
        } else {
            return (" ■  ");
        }
    }

}
