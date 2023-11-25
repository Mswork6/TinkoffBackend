package edu.hw7.Task4;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Main {
    private final static int ITERATIONS = 1000000000;
    private final static int NANOSECONDS_TO_SECONDS = 1000000000;
    private final static Logger LOGGER = LogManager.getLogger();


    @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
    public static void main(String[] args) {
        double startTimeSingle = System.nanoTime();
        SingleThreadCalculation.calculatePi(ITERATIONS);
        double finishTimeSingle = System.nanoTime() - startTimeSingle;
        LOGGER.info("Время работы в однопоточном режиме: " + finishTimeSingle / NANOSECONDS_TO_SECONDS + " секунд");

        double startTimeMulti = System.nanoTime();
        MultiThreadCalculation.calculateMultiThreadPi(6, NANOSECONDS_TO_SECONDS);
        double finishTimeMulti = System.nanoTime() - startTimeMulti;
        LOGGER.info("Время работы в многопоточном режиме: " + finishTimeMulti / NANOSECONDS_TO_SECONDS + " секунд");

        LOGGER.info("среднее время ускорения: " + finishTimeSingle / finishTimeMulti + " раз");

    }
}
