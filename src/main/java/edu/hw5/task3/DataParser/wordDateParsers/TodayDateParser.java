package edu.hw5.task3.DataParser.wordDateParsers;

import edu.hw5.task3.DataParser.DateParser;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class TodayDateParser extends DateParser {
    public TodayDateParser(DateParser nextDateParser) {
        super(nextDateParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String strDate) {
        LocalDate currentDate = LocalDate.now();

        if (Objects.equals(strDate, "today") || Objects.equals(strDate, "Today")) {
            return Optional.of(currentDate);
        }

        return tryParseNext(strDate);
    }
}
