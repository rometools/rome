/*
 * Copyright 2004-2005 Sun Microsystems, Inc.
 *
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
 *
 */
package com.sun.syndication.unittest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import junit.framework.TestCase;

import com.sun.syndication.io.impl.DateParser;

/**
 * 
 * Start of tests for DateParser
 * 
 * @author Nick Lothian
 * 
 */
public class TestDateParser extends TestCase {
    public void testParse() {
        final Calendar cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        // four-digit year
        String sDate = "Tue, 19 Jul 2005 23:00:51 GMT";
        cal.setTime(DateParser.parseRFC822(sDate));

        assertEquals(2005, cal.get(Calendar.YEAR));
        assertEquals(6, cal.get(Calendar.MONTH)); // month is zero-indexed
        assertEquals(19, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(3, cal.get(Calendar.DAY_OF_WEEK));
        assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(51, cal.get(Calendar.SECOND));

        // two-digit year
        sDate = "Tue, 19 Jul 05 23:00:51 GMT";
        cal.setTime(DateParser.parseRFC822(sDate));

        assertEquals(2005, cal.get(Calendar.YEAR));
        assertEquals(6, cal.get(Calendar.MONTH)); // month is zero-indexed
        assertEquals(19, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(3, cal.get(Calendar.DAY_OF_WEEK));
        assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(51, cal.get(Calendar.SECOND));

        // four-digit year
        sDate = "Tue, 19 Jul 2005 23:00:51 UT";
        cal.setTime(DateParser.parseRFC822(sDate));

        assertEquals(2005, cal.get(Calendar.YEAR));
        assertEquals(6, cal.get(Calendar.MONTH)); // month is zero-indexed
        assertEquals(19, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(3, cal.get(Calendar.DAY_OF_WEEK));
        assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(51, cal.get(Calendar.SECOND));

        // two-digit year
        sDate = "Tue, 19 Jul 05 23:00:51 UT";
        cal.setTime(DateParser.parseRFC822(sDate));

        assertEquals(2005, cal.get(Calendar.YEAR));
        assertEquals(6, cal.get(Calendar.MONTH)); // month is zero-indexed
        assertEquals(19, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(3, cal.get(Calendar.DAY_OF_WEEK));
        assertEquals(23, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(51, cal.get(Calendar.SECOND));

        // RFC822
        sDate = "Tue, 19 Jul 2005 23:00:51 GMT";
        assertNotNull(DateParser.parseDate(sDate));

        // RFC822
        sDate = "Tue, 19 Jul 05 23:00:51 GMT";
        assertNotNull(DateParser.parseDate(sDate));

        final Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        c.set(2000, Calendar.JANUARY, 01, 0, 0, 0);
        final Date expectedDate = c.getTime();

        // W3C
        sDate = "2000-01-01T00:00:00Z";
        assertEquals(expectedDate.getTime() / 1000, DateParser.parseDate(sDate).getTime() / 1000);

        // W3C
        sDate = "2000-01-01T00:00Z";
        assertEquals(expectedDate.getTime() / 1000, DateParser.parseDate(sDate).getTime() / 1000);

        // W3C
        sDate = "2000-01-01";
        assertEquals(expectedDate.getTime() / 1000, DateParser.parseDate(sDate).getTime() / 1000);

        // W3C
        sDate = "2000-01";
        assertEquals(expectedDate.getTime() / 1000, DateParser.parseDate(sDate).getTime() / 1000);

        // W3C
        sDate = "2000";
        assertEquals(expectedDate.getTime() / 1000, DateParser.parseDate(sDate).getTime() / 1000);

        // EXTRA
        sDate = "18:10 2000/10/10";
        assertNotNull(DateParser.parseDate(sDate));

        // INVALID
        sDate = "X20:10 2000-10-10";
        assertNull(DateParser.parseDate(sDate));

    }
}
