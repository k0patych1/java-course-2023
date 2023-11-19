package edu.project3;

import java.io.IOException;
import java.util.List;

public final class Main {
    private Main() {}

    public static void main(String[] args) throws IOException {
        String str = new StringBuilder()
            .append("--path ")
            .append("/Users/ruslan/dev/java-course-2023/src/main/java/edu/project3/logs")
            .append(" --from ")
            .append("17/May/2014:13:05:28")
            .append(" --format")
            .append(" markdown")
            .toString();
        String[] args1 = str.split(" ");

        Configuration configuration = CommandLineParser.parseArguments(args1);

        LogProcessor logProcessor = new LogProcessor(configuration);

        List<LogRecord> logs = logProcessor.parseLogs();

        LogReport logReport = new LogReport();
        logReport.analyzeStatistics(logs);

        String fileToWrite = "/Users/ruslan/dev/java-course-2023/src/main/java/edu/project3/logStat2";

        String format = configuration.getOutputFormat();

        if (format == null || format.equals("markdown")) {
            MarkdownOut.writeStatistics(logReport, fileToWrite);
        } else {
            AdocOut.writeStatistics(logReport, fileToWrite);
        }
    }
}
