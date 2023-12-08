package edu.project4;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, Color color) {
    private static final int MIN_COLOR = 0;
    private static final int MAX_COLOR = 255;

    private static double getRandomCoefficient() {
        return ThreadLocalRandom.current().nextDouble(-1, 1);
    }

    public static AffineCoefficients create() {
        double a = getRandomCoefficient();
        double b = getRandomCoefficient();
        double c = getRandomCoefficient();
        double d = getRandomCoefficient();
        double e = getRandomCoefficient();
        double f = getRandomCoefficient();
        while (!validateCoefficients(a, b, d, e)) {
            a = getRandomCoefficient();
            b = getRandomCoefficient();
            c = getRandomCoefficient();
            d = getRandomCoefficient();
            e = getRandomCoefficient();
            f = getRandomCoefficient();
        }
        return new AffineCoefficients(a, b, c, d, e, f,
            new Color(
                ThreadLocalRandom.current().nextInt(MIN_COLOR, MAX_COLOR),
                ThreadLocalRandom.current().nextInt(MIN_COLOR, MAX_COLOR),
                ThreadLocalRandom.current().nextInt(MIN_COLOR, MAX_COLOR)
            )
        );
    }

    private static boolean validateCoefficients(double a, double b, double d, double e) {
        return (Math.pow(a, 2) + Math.pow(d, 2) < 1) && (Math.pow(b, 2) + Math.pow(e, 2) < 1)
            && (Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(d, 2) + Math.pow(e, 2)
            < 1 + (a * e - b * d) * (a * e - b * d));
    }
}
