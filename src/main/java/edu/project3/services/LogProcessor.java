package edu.project3.services;

import edu.project3.entities.Configuration;
import edu.project3.models.LogRecord;
import edu.project3.services.parsers.GlobParser;
import edu.project3.services.parsers.LogsParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogProcessor {
    private final Configuration configuration;

    public LogProcessor(Configuration configuration) {
        this.configuration = configuration;
    }

    public List<LogRecord> getLogs() throws IOException {
        List<LogRecord> logs = new ArrayList<>();
        List<String> logsFilePath = configuration.getLogFilesPath();
        LocalDateTime fromDate = configuration.getFromDate();
        LocalDateTime toDate = configuration.getToDate();
        List<String> urlLogs = configuration.getLogFilesPathURL();

        for (String log : urlLogs) {
            try (BufferedReader brUrl = new BufferedReader(
                new InputStreamReader((new URL(log)).openStream()))) {
                logs.addAll(LogsParser.parseLogsFromBuffer(brUrl, fromDate, toDate));
            }
        }

        for (String log : logsFilePath) {
            List<Path> logFilePaths = GlobParser.getFiles(log);

            for (Path logFile : logFilePaths) {
                try (BufferedReader brFile = Files.newBufferedReader(logFile)) {
                    logs.addAll(LogsParser.parseLogsFromBuffer(brFile, fromDate, toDate));
                }
            }
        }

        return logs;
    }
}
