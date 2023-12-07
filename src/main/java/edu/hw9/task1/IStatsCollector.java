package edu.hw9.task1;

import java.util.List;
import java.util.Map;

public interface IStatsCollector {
    void push(String metricName, double[] data);

    Map<String, Metrics> stats();

    void clear();
}
