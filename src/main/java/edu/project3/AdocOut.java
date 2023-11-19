package edu.project3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public final class AdocOut {
    private static final String BYTES_FORMAT = "bytes%n%n";
    private static final String INT_FORMAT = "%d";

    private AdocOut() {}

    public static void writeStatistics(LogReport logReport, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("= Log Report Statistics\n\n");

            writer.write("== Total Requests\n\n");
            writer.write(String.format(INT_FORMAT + "%n%n", logReport.getTotalRequests()));

            writer.write("== Resource Frequency\n\n");
            writeMap(logReport.getResourceFrequency(), writer);

            writer.write("== Response Code Frequency\n\n");
            writeMap(logReport.getResponseCodeFrequency(), writer);

            writer.write("== Protocol Frequency\n\n");
            writeMap(logReport.getProtocolFrequency(), writer);

            writer.write("== Average Response Size\n\n");
            writer.write(String.format("%.2f " + BYTES_FORMAT, logReport.getAverageResponseSize()));

            writer.write("== Maximum Response Size\n\n");
            writer.write(String.format(INT_FORMAT + " " + BYTES_FORMAT, logReport.getMaximumResponseSize()));

            writer.write("== Minimum Response Size\n\n");
            writer.write(String.format(INT_FORMAT + " " + BYTES_FORMAT, logReport.getMinimumResponseSize()));

            writer.flush();
        } catch (IOException ignored) {

        }
    }

    private static <K> void writeMap(Map<K, Integer> map, FileWriter writer) throws IOException {
        for (Map.Entry<K, Integer> entry : map.entrySet()) {
            writer.write(String.format("%s: " + INT_FORMAT + "%n", entry.getKey(), entry.getValue()));
        }
        writer.write("\n");
    }
}
