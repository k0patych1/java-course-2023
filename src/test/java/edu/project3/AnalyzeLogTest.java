package edu.project3;

import edu.project3.entities.Configuration;
import edu.project3.entities.LogReport;
import edu.project3.models.LogRecord;
import edu.project3.services.LogProcessor;
import edu.project3.services.out.AdocOut;
import edu.project3.services.out.MarkdownOut;
import edu.project3.services.parsers.CommandLineParser;
import edu.project3.services.parsers.GlobParser;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AnalyzeLogTest {
    public static void writeLogsToFile(Path file) throws IOException {
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
    public void globParserTest(@TempDir Path tempDir) throws IOException {
        Path file1 = Files.createFile(tempDir.resolve("logs1.txt"));
        Path file2 = Files.createFile(tempDir.resolve("logs2.txt"));
        Files.createFile(tempDir.resolve("logs3.pages"));
        Files.createFile(tempDir.resolve("laba.txt"));


        List<Path> files = GlobParser.getFiles(tempDir.resolve("log*.txt").toString());

        assertThat(files.size()).isEqualTo(2);
        assertThat(files).contains(file1);
        assertThat(files).contains(file2);
    }

    static List<LogRecord> fileLogs;
    static List<LogRecord> URLLogs;

    @BeforeAll
    public static void getURLLogsTest() throws IOException {
        String url =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        String str = "--path " + url + " --from 17/May/2014:13:05:28 --format markdown";
        String[] args = str.split(" ");

        Configuration configuration = CommandLineParser.parseArguments(args);

        LogProcessor logProcessor = new LogProcessor(configuration);

        URLLogs = logProcessor.getLogs();
    }

    @BeforeAll
    public static void getSomeLogsTest() throws IOException {
        Path file = Files.createTempFile("logs", ".docx");
        String str = "--path " + file + " --from 17/May/2014:13:05:28 --format markdown";
        String[] args = str.split(" ");

        writeLogsToFile(file);

        Configuration configuration = CommandLineParser.parseArguments(args);

        LogProcessor logProcessor = new LogProcessor(configuration);

        fileLogs = logProcessor.getLogs();
    }

    @Test
    public void analyzeStatisticsTest() {
        LogReport logReport = new LogReport();
        logReport.analyzeStatistics(fileLogs);

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
    public void outTest(@TempDir Path tempDir) throws IOException {
        Path fileToWrite = Files.createFile(tempDir.resolve("logStat.txt"));

        LogReport logReport = new LogReport();
        logReport.analyzeStatistics(URLLogs);

        assertDoesNotThrow(() -> new MarkdownOut().writeStatistics(logReport, String.valueOf(fileToWrite)));
        assertDoesNotThrow(() -> new AdocOut().writeStatistics(logReport, String.valueOf(fileToWrite)));
    }
}
