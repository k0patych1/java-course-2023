package edu.hw5.task3.DataParser.countDayParsers;

import edu.hw5.task3.DataParser.DateParser;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeveralDayParser extends DateParser {
    private static final Pattern PATTERN = Pattern.compile("(\\d+) days ago");

    public SeveralDayParser(DateParser nextDateParser) {
        super(nextDateParser);
    }

    @Override
    public Optional<LocalDate> parseDate(String strDate) {
        LocalDate currentDate = LocalDate.now();

        Matcher matcher = PATTERN.matcher(strDate);

        if (matcher.find()) {
            int daysAgo = Integer.parseInt(matcher.group(1));
            return Optional.of(currentDate.minusDays(daysAgo));
        }

        if (nextParser != null) {
            return nextParser.parseDate(strDate);
        }

        return Optional.empty();
    }
}
