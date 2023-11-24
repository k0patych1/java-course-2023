package edu.project3.entities;

import java.time.LocalDateTime;

public class Configuration {
    private String logFilePath;
    private String logFilePathURL;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String outputFormat = "markdown";

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public String getLogFilePathURL() {
        return logFilePathURL;
    }

    public void setLogFilePathURL(String logFilePathURL) {
        this.logFilePathURL = logFilePathURL;
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
