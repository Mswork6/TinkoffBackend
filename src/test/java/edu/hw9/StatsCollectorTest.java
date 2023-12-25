package edu.hw9;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsCollectorTest {

    @Test
    @DisplayName("Проверка работоспособности метода при добавлении нескольких данных")
    public void testNotOneData()
        throws InterruptedException, ExecutionException {
        // given
        StatsCollector collector = new StatsCollector(3);
        collector.push("metric1", new double[] {1.0, 2.0, 3.0});
        collector.push("metric2", new double[] {0.5, 1.5, 2.5, 3.5});

        // when
        List<StatsCollector.StatsResult> results = collector.stats();

        // then
        assertEquals(2, results.size());

        for (StatsCollector.StatsResult result : results) {
            if (result.metricName().equals("metric1")) {
                assertEquals(6.0, result.sum(), 0.001);
                assertEquals(2.0, result.average(), 0.001);
                assertEquals(1.0, result.min(), 0.001);
                assertEquals(3.0, result.max(), 0.001);
            } else if (result.metricName().equals("metric2")) {
                assertEquals(8.0, result.sum(), 0.001);
                assertEquals(2.0, result.average(), 0.001);
                assertEquals(0.5, result.min(), 0.001);
                assertEquals(3.5, result.max(), 0.001);
            }
        }
        collector.shutdown();
    }

    @Test
    @DisplayName("Проверка работоспособности метода при не добавлении данных")
    public void emptyTest()
        throws InterruptedException, ExecutionException {
        // given
        StatsCollector collector = new StatsCollector(3);

        //when
        List<StatsCollector.StatsResult> results = collector.stats();

        // then
        assertEquals(0, results.size());
        collector.shutdown();
    }

    @Test
    @DisplayName("Проверка работоспособности метода при добавлении одних данных")
    public void testSingleData()
        throws InterruptedException, ExecutionException {
        // given
        StatsCollector collector = new StatsCollector(3);
        collector.push("metric1", new double[] {1.0});

        // when
        List<StatsCollector.StatsResult> results = collector.stats();

        // then
        assertEquals(1, results.size());

        StatsCollector.StatsResult result = results.get(0);
        assertEquals("metric1", result.metricName());
        assertEquals(1.0, result.sum(), 0.001);
        assertEquals(1.0, result.average(), 0.001);
        assertEquals(1.0, result.min(), 0.001);
        assertEquals(1.0, result.max(), 0.001);
    }

    @Test
    @DisplayName("Проверка работы метода в многопоточном режиме")
    public void multiThreadTest()
        throws InterruptedException, ExecutionException {
        // given
        StatsCollector collector = new StatsCollector(3);
        Thread thread1 = new Thread(() -> collector.push("metric1", new double[] {1.0, 2.0, 3.0}));
        Thread thread2 = new Thread(() -> collector.push("metric2", new double[] {0.5, 1.5, 2.5, 3.5}));

        // when
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        List<StatsCollector.StatsResult> results = collector.stats();

        // then
        assertEquals(2, results.size());

        for (StatsCollector.StatsResult result : results) {
            if (result.metricName().equals("metric1")) {
                assertEquals(6.0, result.sum(), 0.001);
                assertEquals(2.0, result.average(), 0.001);
                assertEquals(1.0, result.min(), 0.001);
                assertEquals(3.0, result.max(), 0.001);
            } else if (result.metricName().equals("metric2")) {
                assertEquals(8.0, result.sum(), 0.001);
                assertEquals(2.0, result.average(), 0.001);
                assertEquals(0.5, result.min(), 0.001);
                assertEquals(3.5, result.max(), 0.001);
            }
        }
        collector.shutdown();
    }
}
