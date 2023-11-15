package edu.hw5.task3.DataParser.yearMonthDayParsers;

import edu.hw5.task3.DataParser.DateParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class YearMonthDayParser extends DateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuu-M-d");

    public YearMonthDayParser(DateParser nextDateParser) {
        super(nextDateParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String strDate) {
        return parseDateWithFormatter(strDate, FORMATTER);
    }
}
