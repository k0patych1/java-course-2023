package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LogProcessor {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss",
        Locale.ENGLISH);
    public static final int SHIFT_TO_DATE_PARSE = 6;

    private final Configuration configuration;

    public LogProcessor(Configuration configuration) {
        this.configuration = configuration;
    }

    public List<LogRecord> parseLogs() throws IOException {
        List<LogRecord> logs = new ArrayList<>();
        String logFilePath = configuration.getLogFilePath();
        LocalDateTime fromDate = configuration.getFromDate();
        LocalDateTime toDate = configuration.getToDate();
        String urlLogs = configuration.getLogFilePathURL();

        if (urlLogs != null) {
            try (BufferedReader brUrl = new BufferedReader(
                new InputStreamReader((new URL(urlLogs)).openStream()))) {
                logs = parseLogsFromBuffer(brUrl, fromDate, toDate);
            }
        }

        if (logFilePath != null) {
            List<Path> logFilePathes = getLogFiles(logFilePath);

            for (Path logFile : logFilePathes) {
                try (BufferedReader brFile = Files.newBufferedReader(logFile)) {
                    logs.addAll(parseLogsFromBuffer(brFile, fromDate, toDate));
                }
            }
        }

        return logs;
    }

    private List<LogRecord> parseLogsFromBuffer(BufferedReader br, LocalDateTime fromDate, LocalDateTime toDate)
        throws IOException {
        List<LogRecord> logs = new ArrayList<>();

        String line;

        while ((line = br.readLine()) != null) {
            LocalDateTime logEntryTime = parseTime(line);

            if (!(fromDate != null && !logEntryTime.isAfter(fromDate))
                || (toDate != null && !logEntryTime.isBefore(toDate))) {
                LogRecord logRecord = parseLog(line);
                logs.add(logRecord);
            }
        }

        return logs;
    }

    private LocalDateTime parseTime(String s) {
        int startIndexTime = s.indexOf("[");
        int endIndexTime = s.indexOf("]");

        String dateString = s.substring(startIndexTime + 1, endIndexTime - SHIFT_TO_DATE_PARSE);

        return LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
    }

    @SuppressWarnings("MagicNumber")
    private LogRecord parseLog(String line) {
        String[] parts = line.split(" ");

        String ipAddress = parts[0];
        String timestampString = parts[3].substring(1);
        LocalDateTime timestamp = LocalDateTime.parse(timestampString, DATE_TIME_FORMATTER);
        String method = parts[5].substring(1);
        String url = parts[6];
        String protocol = parts[7].substring(0, parts[7].length() - 1);
        int statusCode = Integer.parseInt(parts[8]);
        int responseSize = Integer.parseInt(parts[9]);

        return new LogRecord(ipAddress, timestamp, method, url, protocol, statusCode, responseSize);
    }

    private List<Path> getLogFiles(String path) throws IOException {
        int firstAsteriskIndex = path.indexOf("*");

        if (firstAsteriskIndex == -1) {
            ArrayList<Path> files = new ArrayList<>();
            files.add(Path.of(path));

            return files;
        }

        int lastSlashIndex = path.lastIndexOf("/", firstAsteriskIndex);

        String startDir = path.substring(0, lastSlashIndex);

        var docFileVisitor = new GlobFileVisitor(path);
        Files.walkFileTree(Path.of(startDir), docFileVisitor);

        return docFileVisitor.getMatchedFiles();
    }
}
