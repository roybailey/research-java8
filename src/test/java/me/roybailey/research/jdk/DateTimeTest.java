package me.roybailey.research.jdk;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Created by roybailey on 06/04/2015.
 */
public class DateTimeTest {

    @Test
    public void testDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.DECEMBER, 15, 15, 0, 30);
        System.out.println("without formatting " + dateTime);

        String isoDateTime = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("iso date time " + isoDateTime);

        String isoDate = dateTime.format(DateTimeFormatter.ISO_DATE);
        System.out.println("iso date  " + isoDate);

        String isoTime = dateTime.format(DateTimeFormatter.ISO_TIME);
        System.out.println("iso time  " + isoTime);

        String patternDateTime = dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        System.out.println("using pattern  " + patternDateTime);

        LocalDate fromString = LocalDate.parse("2014-01-20");
        System.out.println("parsed from an string  " + fromString);

        LocalDate parsedFromPatern = LocalDate.parse("2014/03/03", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("using pattern  " + parsedFromPatern);
    }
}
