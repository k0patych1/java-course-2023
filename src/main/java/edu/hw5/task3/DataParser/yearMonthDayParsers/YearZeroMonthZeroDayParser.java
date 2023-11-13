package edu.hw5.task3.DataParser.yearMonthDayParsers;

import edu.hw5.task3.DataParser.DateParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class YearZeroMonthZeroDayParser extends DateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    public YearZeroMonthZeroDayParser(DateParser nextDateParser) {
        super(nextDateParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String strDate) {
        try {
            return Optional.of(LocalDate.parse(strDate, FORMATTER));
        } catch (Exception e) {
            if (nextParser != null) {
                return nextParser.parseDate(strDate);
            }

            return Optional.empty();
        }
    }
}
