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
    public void testW3cCompactPositiveOffset() {
        assertEquals(date("2005-07-19 14:30:42"),
                     DateParser.parseW3CDateTime("2005-07-19T17:00:42+0230", Locale.US));
    }

    @Test
    public void testW3cCompactNegativeOffset() {
        assertEquals(date("2005-07-19 20:30:42"),
                     DateParser.parseW3CDateTime("2005-07-19T17:00:42-0330", Locale.US));
    }

    @Test
    public void testW3cCompactZeroOffset() {
        assertEquals(date("2005-07-19 17:00:42"),
                     DateParser.parseW3CDateTime("2005-07-19T17:00:42+0000", Locale.US));
    }

    @Test
    public void testW3cCompactOffsetWithMilliseconds() {
        assertEquals(new Date(date("2005-07-19 14:30:42").getTime() + 125),
                     DateParser.parseW3CDateTime("2005-07-19T17:00:42.125+0230", Locale.US));
    }

    @Test
    public void testW3cCompactOffsetWithoutSeconds() {
        assertEquals(date("2005-07-19 14:30:00"),
                     DateParser.parseW3CDateTime("2005-07-19T17:00+0230", Locale.US));
    }

    @Test
    public void testParseDateWithCompactOffset() {
        assertEquals(date("2005-07-19 20:00:42"),
                     DateParser.parseDate("2005-07-19T17:00:42-0300", Locale.US));
    }

    @Test
    public void testW3cColonOffset() {
        assertEquals(date("2005-07-19 14:30:42"),
                     DateParser.parseW3CDateTime("2005-07-19T17:00:42+02:30", Locale.US));
        assertEquals(date("2005-07-19 20:30:42"),
                     DateParser.parseW3CDateTime("2005-07-19T17:00:42-03:30", Locale.US));
    }

    @Test
    public void testW3cShortHourOffset() {
        assertEquals(date("2005-07-19 11:30:42"),
                     DateParser.parseW3CDateTime("2005-07-19T17:00:42+5:30", Locale.US));
    }

    @Test
    public void testW3cCommaFraction() {
        assertEquals(date("2005-07-19 14:30:42"),
                     DateParser.parseW3CDateTime("2005-07-19T17:00:42,125+02:30", Locale.US));
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
        assertEquals(date("2005-07-19 17:00:42"),
                     DateParser.parseRFC822("Tue, 19 Jul 05 17:00:42 GMT", Locale.US));
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

    static Date date(String dateString) {
        return date(dateString, TimeZone.getTimeZone("UTC"));
    }

    static Date date(String dateString, TimeZone timeZone) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(timeZone);

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse date", e);
        }
    }
}
