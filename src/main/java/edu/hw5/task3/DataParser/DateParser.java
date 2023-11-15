package edu.hw5.task3.DataParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public abstract class DateParser {
    public DateParser nextParser;

    public DateParser(DateParser nextParser) {
        this.nextParser = nextParser;
    }

    protected Optional<LocalDate> tryParseNext(String strDate) {
        if (nextParser != null) {
            return nextParser.parseDate(strDate);
        }

        return Optional.empty();
    }

    protected Optional<LocalDate> parseDateWithFormatter(String strDate, DateTimeFormatter formatter) {
        try {
            return Optional.of(LocalDate.parse(strDate, formatter));
        } catch (Exception e) {
            return tryParseNext(strDate);
        }
    }

    public abstract Optional<LocalDate> parseDate(String strDate);
}
