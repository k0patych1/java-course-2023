package edu.project3.services.parsers;

import edu.project3.models.LogRecord;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class LogsParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss",
        Locale.ENGLISH
    );

    private LogsParser() {}

    public static List<LogRecord> parseLogsFromBuffer(BufferedReader br, LocalDateTime fromDate, LocalDateTime toDate)
        throws IOException {
        List<LogRecord> logs = new ArrayList<>();

        String line;

        while ((line = br.readLine()) != null) {
            LocalDateTime logEntryTime = TimeParser.parse(line);

            if (!(fromDate != null && !logEntryTime.isAfter(fromDate))
                || (toDate != null && !logEntryTime.isBefore(toDate))) {
                LogRecord logRecord = parseLog(line);
                logs.add(logRecord);
            }
        }

        return logs;
    }

    @SuppressWarnings("MagicNumber")
    private static LogRecord parseLog(String line) {
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
}
