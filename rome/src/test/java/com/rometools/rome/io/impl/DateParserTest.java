package com.rometools.rome.io.impl;

import static org.junit.Assert.assertEquals;

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
    	c.set(2020, 2, 28, 13, 42, 38);
    	c.clear(Calendar.MILLISECOND);
    	c.setTimeZone(TimeZone.getTimeZone("IST"));
    	
    	assertEquals(
                c.getTime(),
                DateParser.parseRFC822("Sa, 28 Mär 20 09:12:38 MEZ", Locale.GERMANY)
        );
    }
}
