package edu.hw5.task3.DataParser.wordDateParsers;

import edu.hw5.task3.DataParser.DateParser;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class YesterdayDateParser extends DateParser {
    public YesterdayDateParser(DateParser nextDateParser) {
        super(nextDateParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String strDate) {
        LocalDate currentDate = LocalDate.now();

        if (Objects.equals(strDate, "yesterday") || Objects.equals(strDate, "Yesterday")) {
            return Optional.of(currentDate.minusDays(1));
        }

        return tryParseNext(strDate);
    }
}
