package edu.project3.services.parsers;

import edu.project3.entities.Configuration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class CommandLineParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss",
        Locale.ENGLISH);

    private CommandLineParser() {}

    public static Configuration parseArguments(String[] args) {
        Configuration configuration = new Configuration();
        int i = 0;
        int len = args.length;

        while (i < len) {
            switch (args[i]) {
                case "--path" -> {
                    String logFilePath = args[++i];
                    if (logFilePath.startsWith("http")) {
                        configuration.setLogFilePathURL(logFilePath);
                    } else {
                        configuration.setLogFilePath(logFilePath);
                    }
                }
                case "--from" -> configuration.setFromDate(LocalDateTime.parse(args[++i], DATE_TIME_FORMATTER));
                case "--to" -> configuration.setToDate(LocalDateTime.parse(args[i], DATE_TIME_FORMATTER));
                case "--format" -> configuration.setOutputFormat(args[++i]);
                default -> throw new IllegalArgumentException("Can't parse this argument: " + args[i]);
            }

            ++i;
        }

        return configuration;
    }
}
