package edu.project3;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AnalyzeLogTest {
    @Test
    public void getLogsTest() {
        String str = "--path /Users/ruslan/dev/java-course-2023/src/main/java/edu/project3/logs --from 17/May/2014:13:05:28 --format markdown";
        String[] args = str.split(" ");

        Configuration configuration = CommandLineParser.parseArguments(args);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss",
            Locale.ENGLISH);

        assertThat(configuration.getLogFilePath()).isEqualTo("/Users/ruslan/dev/java-course-2023/src/main/java/edu/project3/logs");
        assertNull(configuration.getLogFilePathURL());
        assertThat(configuration.getFromDate())
            .isEqualTo(LocalDateTime.parse("17/May/2014:13:05:28", dateTimeFormatter));
        assertNull(configuration.getToDate());
        assertThat(configuration.getOutputFormat()).isEqualTo("markdown");

        LogProcessor logProcessor = new LogProcessor(configuration);

        List<LogRecord> logs = logProcessor.parseLogs();

        assertThat(logs.size()).isEqualTo(3);

        LogRecord expected0 = new LogRecord("93.180.71.3", LocalDateTime.parse("2015-05-17T08:05:32"), "GET", "/downloads/product_1", "HTTP/1.1", 304, 1);
        LogRecord expected1 = new LogRecord("93.180.71.3", LocalDateTime.parse("2015-05-17T08:05:23"), "GET", "/downloads/product_1", "HTTP/1.1", 304, 2);
        LogRecord expected2 = new LogRecord("80.91.33.133", LocalDateTime.parse("2015-05-17T08:05:24"), "GET", "/downloads/product_1", "HTTP/1.1", 304, 3);

        assertThat(logs.get(0)).isEqualTo(expected0);
        assertThat(logs.get(1)).isEqualTo(expected1);
        assertThat(logs.get(2)).isEqualTo(expected2);
    }

    @Test
    public void analyzeStatisticsTest() {
        String str = "--path /Users/ruslan/dev/java-course-2023/src/main/java/edu/project3/logs --from 17/May/2014:13:05:28 --format markdown";
        String[] args = str.split(" ");

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
}
