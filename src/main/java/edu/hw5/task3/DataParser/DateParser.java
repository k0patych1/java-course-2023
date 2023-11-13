package edu.hw5.task3.DataParser;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    public DateParser nextParser;

    public DateParser(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract Optional<LocalDate> parseDate(String strDate);
}
