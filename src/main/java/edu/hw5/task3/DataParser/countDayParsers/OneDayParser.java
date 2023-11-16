package edu.hw5.task3.DataParser.countDayParsers;

import edu.hw5.task3.DataParser.DateParser;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class OneDayParser extends DateParser {
    public OneDayParser(DateParser nextDateParser) {
        super(nextDateParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String strDate) {
        if (Objects.equals(strDate, "one day ago") || Objects.equals(strDate, "1 day ago")) {
            LocalDate localDate = LocalDate.now();

            return Optional.of(localDate.minusDays(1));
        }

        return tryParseNext(strDate);
    }
}
