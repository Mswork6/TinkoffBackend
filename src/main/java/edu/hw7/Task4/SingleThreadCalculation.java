package edu.hw7.Task4;

import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SingleThreadCalculation {
    public static final int CIRCLE_RADIUS = 1;
    public static final double CIRCLE_AMOUNT = Math.pow(CIRCLE_RADIUS, 2);


    @SuppressWarnings("MagicNumber")
    public static double calculatePi(int iterations) {
        Random random = new Random();
        int circlePointCounter = 0;

        for (int i = 0; i < iterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            /* Не совсем понял про nextDouble().У нас действительно значения от 0 до 1
            и мы по факту считаем только правую верхнюю часть круга, но мы потом значения которые получили
            умножаем на 4 и получаем значение для всего круга. По идее это ведь так должно работать
             */

            if (Math.pow(x, 2) + Math.pow(y, 2) <= CIRCLE_AMOUNT) {
                circlePointCounter += 1;
            }
        }

        return 4 * ((double) circlePointCounter / iterations);
        // вот тут мы собственно и получаем значение итоговое для целого круга
    }

}
