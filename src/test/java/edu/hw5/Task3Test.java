package edu.hw5;

import edu.hw5.task3.DataParser.DateParser;
import edu.hw5.task3.DataParser.countDayParsers.OneDayParser;
import edu.hw5.task3.DataParser.countDayParsers.SeveralDayParser;
import edu.hw5.task3.DataParser.dayMonthYearParsers.DayMonthFourDigitYearParser;
import edu.hw5.task3.DataParser.dayMonthYearParsers.DayMonthYearParser;
import edu.hw5.task3.DataParser.wordDateParsers.TodayDateParser;
import edu.hw5.task3.DataParser.wordDateParsers.TomorrowDateParser;
import edu.hw5.task3.DataParser.wordDateParsers.YesterdayDateParser;
import edu.hw5.task3.DataParser.yearMonthDayParsers.YearMonthDayParser;
import edu.hw5.task3.DataParser.yearMonthDayParsers.YearMonthZeroDayParser;
import edu.hw5.task3.DataParser.yearMonthDayParsers.YearZeroMonthDayParser;
import edu.hw5.task3.DataParser.yearMonthDayParsers.YearZeroMonthZeroDayParser;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    private static DateParser getChainOfDateParser() {
        DateParser yearZeroMonthZeroDayParser = new YearZeroMonthZeroDayParser(null);
        DateParser yearMonthZeroDayParser = new YearMonthZeroDayParser(yearZeroMonthZeroDayParser);
        DateParser yearZeroMonthDayParser = new YearZeroMonthDayParser(yearMonthZeroDayParser);
        DateParser yearMonthDayParser = new YearMonthDayParser(yearZeroMonthDayParser);
        DateParser dayMonthFourDigitYearParser = new DayMonthFourDigitYearParser(yearMonthDayParser);
        DateParser dayMonthYearParser = new DayMonthYearParser(dayMonthFourDigitYearParser);
        DateParser tomorrowParser = new TomorrowDateParser(dayMonthYearParser);
        DateParser todayParser = new TodayDateParser(tomorrowParser);
        DateParser yesterdayParser = new YesterdayDateParser(todayParser);
        DateParser severalDayParser = new SeveralDayParser(yesterdayParser);
        return new OneDayParser(severalDayParser);
    }

    private static void checkSpecialDate(String date) {
        DateParser dateParser = getChainOfDateParser();
        Optional<LocalDate> localDate = dateParser.parseDate(date);
        assertTrue(localDate.isPresent());
        assertThat(localDate.get().getDayOfMonth()).isEqualTo(1);
        assertThat(localDate.get().getMonthValue()).isEqualTo(1);
        assertThat(localDate.get().getYear()).isEqualTo(2020);
    }

    @Test
    public void invalidYearFormatTest() {
        String date = "202,0-01-01";

        DateParser dateParser = getChainOfDateParser();
        assertFalse(dateParser.parseDate(date).isPresent());
    }

    @Test
    public void invalidMonthFormatTest() {
        String date = "2020-13-01";

        DateParser dateParser = getChainOfDateParser();
        assertFalse(dateParser.parseDate(date).isPresent());
    }
    @Test
    public void invalidDayFormatTest() {
        String date = "2020-13-32";

        DateParser dateParser = getChainOfDateParser();
        assertFalse(dateParser.parseDate(date).isPresent());
    }


    @Test
    public void yearZeroMonthZeroDayFormatTest() {
        String date = "2020-01-01";

        DateParser dateParser = getChainOfDateParser();
        assertTrue(dateParser.parseDate(date).isPresent());
    }

    @Test
    public void yearMonthZeroDayFormatTest() {
        String date = "2020-1-01";

        checkSpecialDate(date);
    }

    @Test
    public void yearZeroMonthDayFormatTest() {
        String date = "2020-1-01";

        checkSpecialDate(date);
    }

    @Test
    public void yearMonthDayFormatTest() {
        String date = "2020-1-1";

        checkSpecialDate(date);
    }

    @Test
    public void dayMonthFourDigitYearFormatTest() {
        String date = "1-1-2020";

        checkSpecialDate(date);
    }

    @Test
    public void dayMonthFourYearFormatTest() {
        String date = "1-1-2020";

        checkSpecialDate(date);
    }

    @Test
    public void dayMonthYearFormatTest() {
        String date = "1-2-3";

        DateParser dateParser = getChainOfDateParser();
        Optional<LocalDate> localDate = dateParser.parseDate(date);
        assertTrue(localDate.isPresent());
        assertThat(localDate.get().getDayOfMonth()).isEqualTo(1);
        assertThat(localDate.get().getMonthValue()).isEqualTo(2);
        assertThat(localDate.get().getYear()).isEqualTo(3);
    }

    @Test
    public void yesterdayDateTest() {
        String date = "Yesterday";
        LocalDate currentDate = LocalDate.now();

        DateParser dateParser = getChainOfDateParser();
        Optional<LocalDate> localDate = dateParser.parseDate(date);
        assertTrue(localDate.isPresent());
        assertThat(localDate.get()).isEqualTo(currentDate.minusDays(1));
    }

    @Test
    public void todayDateTest() {
        String date = "Today";

        DateParser dateParser = getChainOfDateParser();
        Optional<LocalDate> localDate = dateParser.parseDate(date);
        assertTrue(localDate.isPresent());
        assertThat(localDate.get()).isEqualTo(LocalDate.now());
    }

    @Test
    public void tomorrowDateTest() {
        String date = "Tomorrow";
        LocalDate currentDate = LocalDate.now();

        DateParser dateParser = getChainOfDateParser();
        Optional<LocalDate> localDate = dateParser.parseDate(date);
        assertTrue(localDate.isPresent());
        assertThat(localDate.get()).isEqualTo(currentDate.plusDays(1));
    }

    @Test
    public void oneDayAgoFormatTest() {
        String date = "one day ago";

        LocalDate currentDate = LocalDate.now();
        DateParser dateParser = getChainOfDateParser();
        assertTrue(dateParser.parseDate(date).isPresent());
        assertThat(dateParser.parseDate(date).get()).isEqualTo(currentDate.minusDays(1));
    }

    @Test
    public void oneDayAgoInvalidFormatTest() {
        String date = "one days ago";

        DateParser dateParser = getChainOfDateParser();
        assertFalse(dateParser.parseDate(date).isPresent());
    }

    @Test
    public void severalDayAgoFormatTest() {
        String date = "30 days ago";

        LocalDate currentDate = LocalDate.now();
        DateParser dateParser = getChainOfDateParser();
        assertTrue(dateParser.parseDate(date).isPresent());
        assertThat(dateParser.parseDate(date).get()).isEqualTo(currentDate.minusDays(30));
    }

    @Test
    public void severalDayAgoInvalidFormatTest() {
        String date = "30 day ago";

        DateParser dateParser = getChainOfDateParser();
        assertFalse(dateParser.parseDate(date).isPresent());
    }
}
