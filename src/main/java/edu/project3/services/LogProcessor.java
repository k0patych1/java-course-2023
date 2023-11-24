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
        String logFilePath = configuration.getLogFilePath();
        LocalDateTime fromDate = configuration.getFromDate();
        LocalDateTime toDate = configuration.getToDate();
        String urlLogs = configuration.getLogFilePathURL();

        if (urlLogs != null) {
            try (BufferedReader brUrl = new BufferedReader(
                new InputStreamReader((new URL(urlLogs)).openStream()))) {
                logs.addAll(LogsParser.parseLogsFromBuffer(brUrl, fromDate, toDate));
            }
        }

        if (logFilePath != null) {
            List<Path> logFilePaths = GlobParser.getFiles(logFilePath);

            for (Path logFile : logFilePaths) {
                try (BufferedReader brFile = Files.newBufferedReader(logFile)) {
                    logs.addAll(LogsParser.parseLogsFromBuffer(brFile, fromDate, toDate));
                }
            }
        }

        return logs;
    }
}
