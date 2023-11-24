package edu.project3.entities;

import edu.project3.models.LogRecord;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LogReport {
    private int totalRequests;

    private final Map<String, Integer> resourceFrequency = new HashMap<>();

    private final Map<Integer, Integer> responseCodeFrequency = new HashMap<>();

    private final Map<String, Integer> protocolFrequency = new HashMap<>();

    private int totalResponseSize = 0;

    private int maximumResponseSize = Integer.MIN_VALUE;

    private int minimumResponseSize = Integer.MAX_VALUE;

    public void analyzeStatistics(Collection<LogRecord> logs) {
        totalRequests = logs.size();

        logs.forEach(log -> {
                resourceFrequency.merge(log.url(), 1, Integer::sum);
                responseCodeFrequency.merge(log.statusCode(), 1, Integer::sum);
                protocolFrequency.merge(log.protocol(), 1, Integer::sum);
                totalResponseSize += log.responseSize();

            if (log.responseSize() > maximumResponseSize) {
                maximumResponseSize = log.responseSize();
            }
            if (log.responseSize() < minimumResponseSize) {
                minimumResponseSize = log.responseSize();
            }
            });
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public Map<String, Integer> getResourceFrequency() {
        return resourceFrequency;
    }

    public Map<Integer, Integer> getResponseCodeFrequency() {
        return responseCodeFrequency;
    }

    public Map<String, Integer> getProtocolFrequency() {
        return protocolFrequency;
    }

    public double getAverageResponseSize() {
        if (totalRequests == 0) {
            return 0.0;
        }
        return (double) totalResponseSize / totalRequests;
    }

    public int getMaximumResponseSize() {
        return maximumResponseSize;
    }

    public int getMinimumResponseSize() {
        return minimumResponseSize;
    }
}
