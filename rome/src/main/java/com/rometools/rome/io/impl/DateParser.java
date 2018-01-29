/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.rometools.rome.io.impl;

import com.rometools.rome.io.date.DateConverter;
import com.rometools.rome.io.date.impl.AddedDateConverter;
import com.rometools.rome.io.date.impl.RFC822DateConverter;
import com.rometools.rome.io.date.impl.W3CDateTimeConverter;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A helper class that parses Dates out of Strings with date time in RFC822 and W3CDateTime formats
 * plus the variants Atom (0.3) and RSS (0.9, 0.91, 0.92, 0.93, 0.94, 1.0 and 2.0) specificators
 * added to those formats.
 * <p/>
 * It uses the JDK java.text.SimpleDateFormat class attemtping the parse using a mask for each one
 * of the possible formats.
 * <p/>
 */
public class DateParser {
    private static List<DateConverter> mDateConverters;
    private static DateConverter mW3CDateTimeConverter;
    private static DateConverter mRFC822DateConverter;

    static {
        mDateConverters = new ArrayList<DateConverter>(3);
        mDateConverters.add(mW3CDateTimeConverter = new W3CDateTimeConverter());
        mDateConverters.add(mRFC822DateConverter = new RFC822DateConverter());
        mDateConverters.add(new AddedDateConverter());
    }

    /**
     * Private constructor to avoid DateParser instances creation.
     */
    private DateParser() {
    }

    /**
     * add date date converter
     *
     * @param dateConverter DateConverter
     */
    public static void addDateConverter(DateConverter dateConverter) {
        mDateConverters.add(dateConverter);
    }

    /**
     * Parses a Date out of a String with a date in RFC822 format.
     * <p/>
     * It parsers the following formats:
     * <ul>
     * <li>"EEE, dd MMM yyyy HH:mm:ss z"</li>
     * <li>"EEE, dd MMM yyyy HH:mm z"</li>
     * <li>"EEE, dd MMM yy HH:mm:ss z"</li>
     * <li>"EEE, dd MMM yy HH:mm z"</li>
     * <li>"dd MMM yyyy HH:mm:ss z"</li>
     * <li>"dd MMM yyyy HH:mm z"</li>
     * <li>"dd MMM yy HH:mm:ss z"</li>
     * <li>"dd MMM yy HH:mm z"</li>
     * </ul>
     * <p/>
     * Refer to the java.text.SimpleDateFormat javadocs for details on the format of each element.
     * <p/>
     *
     * @param sDate string to parse for a date.
     * @return the Date represented by the given RFC822 string. It returns <b>null</b> if it was not
     * possible to parse the given string into a Date.
     */
    public static Date parseRFC822(final String sDate, final Locale locale) {
        return mRFC822DateConverter.convert(sDate, locale);
    }

    /**
     * Parses a Date out of a String with a date in W3C date-time format.
     * <p/>
     * It parsers the following formats:
     * <ul>
     * <li>"yyyy-MM-dd'T'HH:mm:ssz"</li>
     * <li>"yyyy-MM-dd'T'HH:mmz"</li>
     * <li>"yyyy-MM-dd"</li>
     * <li>"yyyy-MM"</li>
     * <li>"yyyy"</li>
     * </ul>
     * <p/>
     * Refer to the java.text.SimpleDateFormat javadocs for details on the format of each element.
     * <p/>
     *
     * @param sDate string to parse for a date.
     * @return the Date represented by the given W3C date-time string. It returns <b>null</b> if it
     * was not possible to parse the given string into a Date.
     */
    public static Date parseW3CDateTime(final String sDate, final Locale locale) {
        return mW3CDateTimeConverter.convert(sDate, locale);
    }

    /**
     * Parses a Date out of a String with a date in W3C date-time format or in a RFC822 format.
     * <p>
     *
     * @param sDate string to parse for a date.
     * @return the Date represented by the given W3C date-time string. It returns <b>null</b> if it
     * was not possible to parse the given string into a Date.
     */
    public static Date parseDate(final String sDate, final Locale locale) {
        for (DateConverter converter : mDateConverters) {
            Date date = converter.convert(sDate, locale);
            if (date != null) return date;
        }
        return null;
    }

    /**
     * create a RFC822 representation of a date.
     * <p/>
     * Refer to the java.text.SimpleDateFormat javadocs for details on the format of each element.
     * <p/>
     *
     * @param date Date to parse
     * @return the RFC822 represented by the given Date It returns <b>null</b> if it was not
     * possible to parse the date.
     */
    public static String formatRFC822(final Date date, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", locale);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

    /**
     * create a W3C Date Time representation of a date.
     * <p/>
     * Refer to the java.text.SimpleDateFormat javadocs for details on the format of each element.
     * <p/>
     *
     * @param date Date to parse
     * @return the W3C Date Time represented by the given Date It returns <b>null</b> if it was not
     * possible to parse the date.
     */
    public static String formatW3CDateTime(final Date date, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", locale);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

}
