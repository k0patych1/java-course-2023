package edu.project3.services.out;

import edu.project3.entities.LogReport;
import java.io.IOException;

public interface IOut {
    int OK_CODE = 200;
    int NOT_FOUND_CODE = 404;
    int INTERNAL_SEVER_ERROR_CODE = 500;

    void writeStatistics(LogReport logReport, String filePath) throws IOException;

    default String getCodeName(int code) {
        return switch (code) {
            case OK_CODE -> "OK";
            case NOT_FOUND_CODE -> "Not Found";
            case INTERNAL_SEVER_ERROR_CODE -> "Internal Server Error";
            default -> "";
        };
    }
}
