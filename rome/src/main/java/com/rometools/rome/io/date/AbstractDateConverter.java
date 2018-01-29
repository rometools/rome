package com.rometools.rome.io.date;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class AbstractDateConverter implements DateConverter {

    protected static String convertUnsupportedTimeZones(String sDate) {
        final List<String> unsupportedZeroOffsetTimeZones = Arrays.asList("UT", "Z");

        for (String timeZone : unsupportedZeroOffsetTimeZones) {
            if (sDate.endsWith(timeZone)) {
                return replaceLastOccurrence(sDate, timeZone, "UTC");
            }
        }
        return sDate;
    }

    protected static String replaceLastOccurrence(String original, String target, String replacement) {
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
     * Parses a Date out of a string using an array of masks.
     * <p/>
     * It uses the masks in order until one of them succedes or all fail.
     * <p/>
     *
     * @param masks array of masks to use for parsing the string
     * @param sDate string to parse for a date.
     * @return the Date represented by the given string using one of the given masks. It returns
     * <b>null</b> if it was not possible to parse the the string with any of the masks.
     */
    protected static Date parseUsingMask(final String[] masks, String sDate, final Locale locale) {
        if (sDate != null) {
            sDate = sDate.trim();
        } else {
            return null;
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
                if (d != null) return d;
            } catch (final Exception ex1) {
                // pass
            }
        }
        return d;
    }

}