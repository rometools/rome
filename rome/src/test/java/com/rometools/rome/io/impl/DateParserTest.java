package com.rometools.rome.io.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Locale;
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
}
