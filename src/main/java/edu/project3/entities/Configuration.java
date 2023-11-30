package edu.project3.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Configuration {
    private final List<String> logFilesPath;
    private final List<String> logFilesPathURL;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String outputFormat = "markdown";

    public Configuration() {
        this.logFilesPath = new ArrayList<>();
        this.logFilesPathURL = new ArrayList<>();
    }

    public List<String> getLogFilesPath() {
        return logFilesPath;
    }

    public void addLogFilesPath(String logFilePath) {
        logFilesPath.add(logFilePath);
    }

    public List<String> getLogFilesPathURL() {
        return logFilesPathURL;
    }

    public void addLogFilesPathURL(String logFilePathURL) {
        logFilesPathURL.add(logFilePathURL);
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }
}
