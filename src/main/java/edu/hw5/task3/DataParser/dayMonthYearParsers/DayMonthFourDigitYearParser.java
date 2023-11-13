package edu.hw5.task3.DataParser.dayMonthYearParsers;

import edu.hw5.task3.DataParser.DateParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DayMonthFourDigitYearParser extends DateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-uuuu");

    public DayMonthFourDigitYearParser(DateParser nextDateParser) {
        super(nextDateParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String strDate) {
        try {
            return Optional.of(LocalDate.parse(strDate, FORMATTER));
        } catch (DateTimeParseException e) {
            if (nextParser != null) {
                return nextParser.parseDate(strDate);
            }

            return Optional.empty();
        }
    }
}
