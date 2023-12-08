package edu.project4.Renderers;

import edu.project4.AffineCoefficients;
import edu.project4.Point;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RendererProcessor {

    public static AffineCoefficients[] getCoefficients(int amount) {
        return Stream.generate(AffineCoefficients::create)
            .limit(amount)
            .toArray(AffineCoefficients[]::new);
    }

    public static AffineCoefficients getRandomCoefficient(AffineCoefficients[] arr) {
        int randIndex = ThreadLocalRandom.current().nextInt(arr.length);
        return arr[randIndex];
    }

    public static Point newPoint(AffineCoefficients coeffs, Point point) {
        double newX = coeffs.a() * point.x() + coeffs.b() * point.y() + coeffs.c();
        double newY = coeffs.d() * point.x() + coeffs.e() * point.y() + coeffs.f();
        return new Point(newX, newY);
    }

    public static Point rotatePoint(Point point, double angle) {
        double xRot = point.x() * Math.cos(angle) - point.y() * Math.sin(angle);
        double yRot = point.x() * Math.sin(angle) + point.y() * Math.cos(angle);
        return new Point(xRot, yRot);
    }

    public static Transformation randomTransformation(List<Transformation> transformations) {
        return transformations.get(ThreadLocalRandom.current().nextInt(transformations.size()));
    }

}
