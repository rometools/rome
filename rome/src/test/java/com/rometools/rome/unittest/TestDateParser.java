/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.rome.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.rometools.rome.io.impl.DateParser;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TestDateParser {

    @Test
    public void testW3c() {
        assertEquals(date("2005-07-19 17:00:42"),
                DateParser.parseW3CDateTime("2005-07-19T17:00:42Z", Locale.US));
    }

    @Test
    public void testW3cNoSeconds() {
        assertEquals(date("2005-07-19 17:00:00"),
                DateParser.parseW3CDateTime("2005-07-19T17:00Z", Locale.US));
    }

    @Test
    public void testW3cNoTime() {
        assertEquals(date("2005-07-19 00:00:00"),
                DateParser.parseW3CDateTime("2005-07-19", Locale.US));
    }

    @Test
    public void testW3cOnlyYearAndMonth() {
        assertEquals(date("2005-07-01 00:00:00"),
                DateParser.parseW3CDateTime("2005-07", Locale.US));
    }

    @Test
    public void testW3cOnlyYear() {
        assertEquals(date("2005-01-01 00:00:00"),
                DateParser.parseW3CDateTime("2005", Locale.US));
    }

    @Test
    public void testRfc822FourDigitYear() {
        assertEquals(date("2005-07-19 17:00:42"),
                DateParser.parseRFC822("Tue, 19 Jul 2005 17:00:42 GMT", Locale.US));
    }

    @Test
    public void testRfc822TwoDigitYear() {
        assertEquals(
                date("2005-07-19 17:00:42"),
                DateParser.parseRFC822("Tue, 19 Jul 05 17:00:42 GMT", Locale.US)
        );
    }

    @Test
    public void testRfc822WithUtTimeZone() {
        assertEquals(date("2005-07-19 17:00:42"),
                DateParser.parseRFC822("Tue, 19 Jul 2005 17:00:42 UT", Locale.US));
    }

    @Test
    public void testRfc822WithUtcTimeZone() {
        assertEquals(date("2005-07-19 17:00:42"),
                DateParser.parseRFC822("Tue, 19 Jul 2005 17:00:42 UTC", Locale.US));
    }

    @Test
    public void testRfc822WithZTimeZone() {
        assertEquals(date("2005-07-19 17:00:42"),
                DateParser.parseRFC822("Tue, 19 Jul 2005 17:00:42 Z", Locale.US));
    }

    @Test
    public void testExtraMaskInRomePropertiesFile() {
        assertEquals(date("2005-07-19 17:00:00", TimeZone.getDefault()),
                DateParser.parseDate("17:00 2005/07/19", Locale.US));
    }

    @Test
    public void testInvalidDate() {
        assertNull(DateParser.parseDate("X00:00 2005-07-19", Locale.US));
    }

    static LocalDateTime date(String dateString) {
        return date(dateString, TimeZone.getTimeZone("GMT"));
    }

    static LocalDateTime date(String dateString, TimeZone timeZone) {

        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateFormat.withZone(timeZone.toZoneId());

        try {
            return LocalDateTime.parse(dateString, dateFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Failed to parse date", e);
        }
    }
}
