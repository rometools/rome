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

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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

    private static String[] ADDITIONAL_MASKS;

    // order is like this because the SimpleDateFormat.parse does not fail with exception if it can
    // parse a valid date out of a substring of the full string given the mask so we have to check
    // the most complete format first, then it fails with exception
    private static final String[] RFC822_MASKS = { "EEE, dd MMM yy HH:mm:ss z", "EEE, dd MMM yy HH:mm z", "dd MMM yy HH:mm:ss z", "dd MMM yy HH:mm z" };

    // order is like this because the SimpleDateFormat.parse does not fail with exception if it can
    // parse a valid date out of a substring of the full string given the mask so we have to check
    // the most complete format first, then it fails with exception
    private static final String[] W3CDATETIME_MASKS = { "yyyy-MM-dd'T'HH:mm:ss.SSSz", "yyyy-MM-dd't'HH:mm:ss.SSSz", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd't'HH:mm:ss.SSS'z'", "yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd't'HH:mm:ssz", "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd't'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd't'HH:mm:ss'z'", "yyyy-MM-dd'T'HH:mmz", // together
            // with
            // logic
            // in
            // the
            // parseW3CDateTime
            // they
            "yyyy-MM'T'HH:mmz", // handle W3C dates without time forcing them to
            // be GMT
            "yyyy'T'HH:mmz", "yyyy-MM-dd't'HH:mmz", "yyyy-MM-dd'T'HH:mm'Z'", "yyyy-MM-dd't'HH:mm'z'", "yyyy-MM-dd", "yyyy-MM", "yyyy" };

    /**
     * The masks used to validate and parse the input to this Atom date. These are a lot more
     * forgiving than what the Atom spec allows. The forms that are invalid according to the spec
     * are indicated.
     */
    @SuppressWarnings("unused")
    private static final String[] masks = { "yyyy-MM-dd'T'HH:mm:ss.SSSz", "yyyy-MM-dd't'HH:mm:ss.SSSz", // invalid
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd't'HH:mm:ss.SSS'z'", // invalid
            "yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd't'HH:mm:ssz", // invalid
            "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd't'HH:mm:ss'z'", // invalid
            "yyyy-MM-dd'T'HH:mmz", // invalid
            "yyyy-MM-dd't'HH:mmz", // invalid
            "yyyy-MM-dd'T'HH:mm'Z'", // invalid
            "yyyy-MM-dd't'HH:mm'z'", // invalid
            "yyyy-MM-dd", "yyyy-MM", "yyyy" };

    static {
        ADDITIONAL_MASKS = PropertiesLoader.getPropertiesLoader().getTokenizedProperty("datetime.extra.masks", "|");
    }

    /**
     * Private constructor to avoid DateParser instances creation.
     */
    private DateParser() {
    }

    /**
     * Parses a Date out of a string using an array of masks.
     * <p/>
     * It uses the masks in order until one of them succedes or all fail.
     * <p/>
     *
     * @param masks array of masks to use for parsing the string
     * @param sDate string to parse for a date.
     * @return the Date represented by the given string using one of the given masks. It returns
     *         <b>null</b> if it was not possible to parse the the string with any of the masks.
     *
     */
    private static Date parseUsingMask(final String[] masks, String sDate, final Locale locale) {
        if (sDate != null) {
            sDate = sDate.trim();
        }
        ParsePosition pp = null;
        Date d = null;
        for (int i = 0; d == null && i < masks.length; i++) {
            final DateFormat df = new SimpleDateFormat(masks[i], locale);
            // df.setLenient(false);
            df.setLenient(true);
            try {
                pp = new ParsePosition(0);
                d = df.parse(sDate, pp);
                if (pp.getIndex() != sDate.length()) {
                    d = null;
                }
            } catch (final Exception ex1) {
            }
        }
        return d;
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
     *         possible to parse the given string into a Date.
     *
     */
    public static Date parseRFC822(String sDate, final Locale locale) {
        sDate = convertUnsupportedTimeZones(sDate);
        return parseUsingMask(RFC822_MASKS, sDate, locale);
    }

    private static String convertUnsupportedTimeZones(String sDate) {
        final List<String> unsupportedZeroOffsetTimeZones = Arrays.asList("UT", "Z");

        for (String timeZone : unsupportedZeroOffsetTimeZones) {
            if (sDate.endsWith(timeZone)) {
                return replaceLastOccurrence(sDate, timeZone, "UTC");
            }
        }
        return sDate;
    }

    private static String replaceLastOccurrence(String original, String target, String replacement) {
        final int lastIndexOfTarget = original.lastIndexOf(target);

        if (lastIndexOfTarget == -1) {
            return original;
        } else {
            return new StringBuilder(original)
                .replace(lastIndexOfTarget, lastIndexOfTarget + target.length(), replacement)
                .toString();
        }
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
     *         was not possible to parse the given string into a Date.
     *
     */
    public static Date parseW3CDateTime(String sDate, final Locale locale) {
        // if sDate has time on it, it injects 'GTM' before de TZ displacement to allow the
        // SimpleDateFormat parser to parse it properly
        final int tIndex = sDate.indexOf("T");
        if (tIndex > -1) {
            if (sDate.endsWith("Z")) {
                sDate = sDate.substring(0, sDate.length() - 1) + "+00:00";
            }
            int tzdIndex = sDate.indexOf("+", tIndex);
            if (tzdIndex == -1) {
                tzdIndex = sDate.indexOf("-", tIndex);
            }
            if (tzdIndex > -1) {
                String pre = sDate.substring(0, tzdIndex);
                final int secFraction = pre.indexOf(",");
                if (secFraction > -1) {
                    pre = pre.substring(0, secFraction);
                }
                final String post = sDate.substring(tzdIndex);
                sDate = pre + "GMT" + post;
            }
        } else {
            sDate += "T00:00GMT";
        }
        return parseUsingMask(W3CDATETIME_MASKS, sDate, locale);
    }

    /**
     * Parses a Date out of a String with a date in W3C date-time format or in a RFC822 format.
     * <p>
     *
     * @param sDate string to parse for a date.
     * @return the Date represented by the given W3C date-time string. It returns <b>null</b> if it
     *         was not possible to parse the given string into a Date.
     *
     * */
    public static Date parseDate(final String sDate, final Locale locale) {
        Date date = parseW3CDateTime(sDate, locale);
        if (date == null) {
            date = parseRFC822(sDate, locale);
            if (date == null && ADDITIONAL_MASKS.length > 0) {
                date = parseUsingMask(ADDITIONAL_MASKS, sDate, locale);
            }
        }
        return date;
    }

    /**
     * create a RFC822 representation of a date.
     * <p/>
     * Refer to the java.text.SimpleDateFormat javadocs for details on the format of each element.
     * <p/>
     *
     * @param date Date to parse
     * @return the RFC822 represented by the given Date It returns <b>null</b> if it was not
     *         possible to parse the date.
     *
     */
    public static String formatRFC822(final Date date, final Locale locale) {
        final SimpleDateFormat dateFormater = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", locale);
        dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormater.format(date);
    }

    /**
     * create a W3C Date Time representation of a date.
     * <p/>
     * Refer to the java.text.SimpleDateFormat javadocs for details on the format of each element.
     * <p/>
     *
     * @param date Date to parse
     * @return the W3C Date Time represented by the given Date It returns <b>null</b> if it was not
     *         possible to parse the date.
     *
     */
    public static String formatW3CDateTime(final Date date, final Locale locale) {
        final SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", locale);
        dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormater.format(date);
    }

}
