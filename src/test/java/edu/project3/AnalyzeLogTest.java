package edu.project3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalyzeLogTest {
    public void writeLogsToFile(Path file) throws IOException {
        try (BufferedWriter br = Files.newBufferedWriter(file)) {
            String log1 = new StringBuilder()
                .append("93.180.71.3")
                .append(" - - [17/May/2015:08:05:32 +0000]")
                .append(" \"GET /downloads/product_1 HTTP/1.1\"")
                .append(" 304 1 \"-\" \"Debian APT-HTTP/1.3")
                .append(" (0.8.16~exp12ubuntu10.21)\"")
                .toString();

            String log2 = new StringBuilder()
                .append("93.180.71.3")
                .append(" - - [17/May/2015:08:05:23 +0000]")
                .append(" \"GET /downloads/product_1 HTTP/1.1\"")
                .append(" 304 2 \"-\" \"Debian APT-HTTP/1.3")
                .append(" (0.8.16~exp12ubuntu10.21)\"")
                .toString();

            String log3 = new StringBuilder()
                .append("80.91.33.133")
                .append(" - - [17/May/2015:08:05:24 +0000]")
                .append(" \"GET /downloads/product_1 HTTP/1.1\"")
                .append(" 304 3 \"-\" \"Debian APT-HTTP/1.3")
                .append(" (0.8.16~exp12ubuntu10.17)\"")
                .toString();

            br.write(log1);
            br.newLine();
            br.write(log2);
            br.newLine();
            br.write(log3);
            br.newLine();
        }
    }

    @Test
    public void getLogsTest(@TempDir Path tempDir) throws IOException {
        Path file = Files.createFile(tempDir.resolve("logs"));
        String str = "--path " + file + " --from 17/May/2014:13:05:28 --format markdown";
        String[] args = str.split(" ");

        writeLogsToFile(file);

        Configuration configuration = CommandLineParser.parseArguments(args);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss",
            Locale.ENGLISH);

        assertThat(configuration.getLogFilePath()).isEqualTo(file.toString());
        assertNull(configuration.getLogFilePathURL());
        assertThat(configuration.getFromDate())
            .isEqualTo(LocalDateTime.parse("17/May/2014:13:05:28", dateTimeFormatter));
        assertNull(configuration.getToDate());
        assertThat(configuration.getOutputFormat()).isEqualTo("markdown");

        LogProcessor logProcessor = new LogProcessor(configuration);

        List<LogRecord> logs = logProcessor.parseLogs();

        assertThat(logs.size()).isEqualTo(3);

        LogRecord expected0 = new LogRecord(
            "93.180.71.3",
            LocalDateTime.parse("2015-05-17T08:05:32"),
            "GET", "/downloads/product_1",
            "HTTP/1.1", 304, 1);

        LogRecord expected1 = new LogRecord("93.180.71.3",
            LocalDateTime.parse("2015-05-17T08:05:23"),
            "GET", "/downloads/product_1",
            "HTTP/1.1", 304, 2);

        LogRecord expected2 = new LogRecord(
            "80.91.33.133",
            LocalDateTime.parse("2015-05-17T08:05:24"),
            "GET", "/downloads/product_1",
            "HTTP/1.1", 304, 3);

        assertThat(logs.get(0)).isEqualTo(expected0);
        assertThat(logs.get(1)).isEqualTo(expected1);
        assertThat(logs.get(2)).isEqualTo(expected2);
    }

    @Test
    public void analyzeStatisticsTest(@TempDir Path tempDir) throws IOException {
        Path file = Files.createFile(tempDir.resolve("logs"));
        String str = "--path " + file + " --from 17/May/2014:13:05:28 --format markdown";
        String[] args = str.split(" ");

        writeLogsToFile(file);

        Configuration configuration = CommandLineParser.parseArguments(args);

        LogProcessor logProcessor = new LogProcessor(configuration);

        List<LogRecord> logs = logProcessor.parseLogs();

        LogReport logReport = new LogReport();
        logReport.analyzeStatistics(logs);

        assertThat(logReport.getAverageResponseSize()).isEqualTo(2);
        assertThat(logReport.getMinimumResponseSize()).isEqualTo(1);
        assertThat(logReport.getMaximumResponseSize()).isEqualTo(3);
        assertThat(logReport.getTotalRequests()).isEqualTo(3);
        assertThat(logReport.getProtocolFrequency().size()).isEqualTo(1);
        assertThat(logReport.getProtocolFrequency().get("HTTP/1.1")).isEqualTo(3);
        assertThat(logReport.getResourceFrequency().size()).isEqualTo(1);
        assertThat(logReport.getResourceFrequency().get("/downloads/product_1")).isEqualTo(3);
        assertThat(logReport.getResponseCodeFrequency().size()).isEqualTo(1);
        assertThat(logReport.getResponseCodeFrequency().get(304)).isEqualTo(3);
    }

    @Test

    public void getLogsFromUrlTest() throws IOException {
        String url =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        String str = "--path " + url + " --from 17/May/2014:13:05:28 --format markdown";
        String[] args = str.split(" ");

        Configuration configuration = CommandLineParser.parseArguments(args);

        LogProcessor logProcessor = new LogProcessor(configuration);

        List<LogRecord> logs = logProcessor.parseLogs();

        LogReport logReport = new LogReport();
        logReport.analyzeStatistics(logs);

        assertTrue(logs.size() > 100);

        LogRecord firstLog = logs.get(0);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
            "dd/MMM/yyyy:HH:mm:ss",
            Locale.ENGLISH
        );

        assertThat(firstLog.ipAddress()).isEqualTo("93.180.71.3");
        assertThat(firstLog.timestamp()).isEqualTo((LocalDateTime.parse("17/May/2015:08:05:32", dateTimeFormatter)));
        assertThat(firstLog.url()).isEqualTo("/downloads/product_1");
        assertThat(firstLog.protocol()).isEqualTo("HTTP/1.1");
        assertThat(firstLog.statusCode()).isEqualTo(304);
        assertThat(firstLog.responseSize()).isEqualTo(0);
    }
}
