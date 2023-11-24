package edu.project3.services.out;

import edu.project3.entities.LogReport;
import java.io.IOException;

public interface IOut {
    void writeStatistics(LogReport logReport, String filePath) throws IOException;
}
