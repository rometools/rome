package com.rometools.rome.date.impl;

import com.rometools.rome.date.AbstractDateConverter;

import java.util.Date;
import java.util.Locale;

public class RFC822DateConverter extends AbstractDateConverter {

    // order is like this because the SimpleDateFormat.parse does not fail with exception if it can
    // parse a valid date out of a substring of the full string given the mask so we have to check
    // the most complete format first, then it fails with exception
    private static final String[] RFC822_MASKS = {
            "EEE, dd MMM yy HH:mm:ss z",
            "EEE, dd MMM yy HH:mm z",
            "dd MMM yy HH:mm:ss z",
            "dd MMM yy HH:mm z"
    };


    @Override
    public Date convert(final String sDate, final Locale locale) {
        Date date = parseRFC822(sDate, locale);
        if (date == null && !Locale.getDefault().equals(locale)) {
            date = parseRFC822(sDate, Locale.getDefault());
        }
        return date;
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
    private static Date parseRFC822(String sDate, final Locale locale) {
        sDate = convertUnsupportedTimeZones(sDate);
        return parseUsingMask(RFC822_MASKS, sDate, locale);
    }
}
