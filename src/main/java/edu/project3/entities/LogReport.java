package edu.project3.entities;

import edu.project3.models.LogRecord;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogReport {
    private List<String> files;

    private List<String> urls;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    private long totalRequests = 0;

    private final Map<String, Integer> resourceFrequency = new HashMap<>();

    private final Map<Integer, Integer> responseCodeFrequency = new HashMap<>();

    private final Map<String, Integer> protocolFrequency = new HashMap<>();

    private long totalResponseSize = 0;

    private long maximumResponseSize = Integer.MIN_VALUE;

    private long minimumResponseSize = Integer.MAX_VALUE;

    public void processingConfiguration(Configuration configuration) {
        this.files = configuration.getLogFilesPath();
        this.urls = configuration.getLogFilesPathURL();
        this.fromDate = configuration.getFromDate();
        this.toDate = configuration.getToDate();
    }

    public void analyzeStatistics(Collection<LogRecord> logs) {
        totalRequests = logs.size();

        for (LogRecord log : logs) {
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
        }
    }

    public long getTotalRequests() {
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

    public long getMaximumResponseSize() {
        return maximumResponseSize;
    }

    public long getMinimumResponseSize() {
        return minimumResponseSize;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public List<String> getFiles() {
        return files;
    }

    public List<String> getUrls() {
        return urls;
    }
}
