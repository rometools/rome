package com.rometools.utils;

import java.util.Date;

public final class Dates {

    private Dates() {
    }

    /**
     * Creates a copy on a Date.
     *
     * @param d The Date to copy, can be null
     * @return null when the input Date was null, a copy of the Date otherwise
     */
    public static Date copy(final Date d) {
        if (d == null) {
            return null;
        } else {
            return new Date(d.getTime());
        }
    }

}
