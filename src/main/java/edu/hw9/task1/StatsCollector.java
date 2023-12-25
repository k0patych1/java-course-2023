package edu.hw9.task1;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector implements IStatsCollector {
    private final Map<String, double[]> bigData;

    private final ConcurrentMap<String, Metrics> cachedMetrics;

    public StatsCollector() {
        bigData = new HashMap<>();
        cachedMetrics = new ConcurrentHashMap<>();
    }

    @Override
    public void push(String metricName, double[] data) {
        if (data.length == 0) {
            throw new IllegalArgumentException("Metric name \"" + metricName + "\" has empty data");
        }

        bigData.put(metricName, data);
    }

    private void calculateMetrics(String metricName, double[] data) {
        DoubleSummaryStatistics stats = Arrays.stream(data)
            .summaryStatistics();

        var metrics = new Metrics(stats.getMax(), stats.getMin(), stats.getSum(), stats.getAverage());

        cachedMetrics.put(metricName, metrics);
    }

    @Override
    public Map<String, Metrics> stats() {
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        try (ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads)) {
            for (Map.Entry<String, double[]> entry : bigData.entrySet()) {
                String metricName = entry.getKey();
                double[] metricData = entry.getValue();

                if (!cachedMetrics.containsKey(metricName)) {
                    executorService.submit(() -> calculateMetrics(metricName, metricData));
                }
            }
        }

        return cachedMetrics;
    }

    @Override
    public void clear() {
        bigData.clear();
        cachedMetrics.clear();
    }
}
