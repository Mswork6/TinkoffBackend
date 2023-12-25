package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, double width, double height) {

    public boolean contains(Point p) {
        return p.x() >= x && p.x() < width + x && p.y() >= y && p.y() < height + y;
    }

    public Point getPoint() {
        return new Point(
            ThreadLocalRandom.current().nextDouble(0, width),
            ThreadLocalRandom.current().nextDouble(0, height)
        );
    }
}
