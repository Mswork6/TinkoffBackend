package edu.hw9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {
    private final Map<String, List<Double>> data;
    private final ExecutorService executor;

    public StatsCollector(int numThreads) {
        this.data = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(numThreads);
    }

    public void push(String metricName, double[] values) {
        if (!data.containsKey(metricName)) {
            data.put(metricName, Collections.synchronizedList(new ArrayList<>()));
        }
        List<Double> list = data.get(metricName);
        for (double value : values) {
            list.add(value);
        }
    }

    public List<StatsResult> stats() throws InterruptedException, ExecutionException {
        List<StatsResult> results = new ArrayList<>();
        List<Future<StatsResult>> futures = new ArrayList<>();

        for (Map.Entry<String, List<Double>> entry : data.entrySet()) {
            String metricName = entry.getKey();
            List<Double> values = entry.getValue();

            Callable<StatsResult> task = () -> {
                double sum = 0;
                double min = Double.MAX_VALUE;
                double max = Double.MIN_VALUE;

                for (double value : values) {
                    sum += value;
                    min = Math.min(min, value);
                    max = Math.max(max, value);
                }

                double average = sum / values.size();
                return new StatsResult(metricName, sum, average, min, max);
            };

            futures.add(executor.submit(task));
        }

        for (Future<StatsResult> future : futures) {
            results.add(future.get());
        }

        return results;
    }

    public void shutdown() {
        executor.shutdown();
    }

    public record StatsResult(String metricName, double sum, double average, double min, double max) {

    }
}
