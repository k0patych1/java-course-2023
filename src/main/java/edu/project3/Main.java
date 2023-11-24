package edu.project3;

import edu.project3.entities.Configuration;
import edu.project3.entities.LogReport;
import edu.project3.models.LogRecord;
import edu.project3.services.LogProcessor;
import edu.project3.services.out.AdocOut;
import edu.project3.services.out.MarkdownOut;
import edu.project3.services.parsers.CommandLineParser;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public final class Main {
    private Main() {}

    public static void main(String[] args) throws IOException {
        Configuration configuration = CommandLineParser.parseArguments(args);

        LogProcessor logProcessor = new LogProcessor(configuration);

        List<LogRecord> logs = logProcessor.getLogs();

        LogReport logReport = new LogReport();
        logReport.analyzeStatistics(logs);

        String fileToWrite = Path.of(System.getProperty("user. dir")).resolve("logstat").toString();

        String format = configuration.getOutputFormat();

        if (format == null || format.equals("markdown")) {
            new MarkdownOut().writeStatistics(logReport, fileToWrite);
        } else {
            new AdocOut().writeStatistics(logReport, fileToWrite);
        }
    }
}
