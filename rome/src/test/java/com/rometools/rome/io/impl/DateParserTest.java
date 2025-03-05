package com.rometools.rome.io.impl;

import static org.junit.Assert.assertEquals;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

public class DateParserTest {

    @Test
    public void parseW3CDateTimeIsOk() throws Exception {
        assertEquals(
                new Date(1000),
                DateParser.parseW3CDateTime("1970-01-01T00:00:01+00:00", Locale.GERMANY)
        );
    }

    @Test
    public void parseW3CDateTimeWithTrailingWhitespaceIsOk() throws Exception {
        assertEquals(
                new Date(1000),
                DateParser.parseW3CDateTime("1970-01-01T00:00:01+00:00   ", Locale.GERMANY)
        );
    }
    
    @Test
    public void parseRFC822DateTimeWithTimeZoneIsOk() throws Exception {
    	// Sat, 28 Mar 2020 13:42:38 IST
    	Calendar c = Calendar.getInstance();
    	c.set(2020, Calendar.MARCH, 28, 13, 42, 38);
    	c.clear(Calendar.MILLISECOND);
    	c.setTimeZone(TimeZone.getTimeZone("IST"));
        DateFormatSymbols german = new DateFormatSymbols(Locale.GERMANY);
        String saturday = german.getShortWeekdays()[7];
        String march = german.getShortMonths()[2];

    	assertEquals(
                c.getTime(),
                DateParser.parseRFC822(
                    String.format("%s, 28 %s 20 09:12:38 MEZ", saturday, march),
                    Locale.GERMANY
                )
        );
    }
}
