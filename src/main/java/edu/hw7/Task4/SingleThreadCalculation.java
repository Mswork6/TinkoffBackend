package edu.hw7.Task4;

import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SingleThreadCalculation {
    public static final int CIRCLE_RADIUS = 1;

    @SuppressWarnings("MagicNumber")
    public static double calculatePi(int iterations) {
        Random random = new Random();
        int circlePointCounter = 0;

        for (int i = 0; i < iterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(CIRCLE_RADIUS, 2)) {
                circlePointCounter += 1;
            }
        }

        return 4 * ((double) circlePointCounter / iterations);
    }

}
