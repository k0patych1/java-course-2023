package edu.hw5.task3.DataParser.wordDateParsers;

import edu.hw5.task3.DataParser.DateParser;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class TomorrowDateParser extends DateParser {
    public TomorrowDateParser(DateParser nextDateParser) {
        super(nextDateParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String strDate) {
        LocalDate currentDate = LocalDate.now();

        if (Objects.equals(strDate, "tomorrow") || Objects.equals(strDate, "Tomorrow")) {
            return Optional.of(currentDate.plusDays(1));
        }

        if (nextParser != null) {
            return nextParser.parseDate(strDate);
        }

        return Optional.empty();
    }
}
