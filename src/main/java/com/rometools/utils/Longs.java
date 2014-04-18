package com.rometools.utils;

public final class Longs {

    private Longs() {
    }

    /**
     * Converts a String into a Long by first parsing it as Double and then casting it to Long.
     *
     * @param s The String to convert, may be null or in decimal format
     * @return The parsed Long or null when parsing is not possible
     */
    public static Long parseDecimal(final String s) {
        Long parsed = null;
        try {
            if (s != null) {
                parsed = (long) Double.parseDouble(s);
            }
        } catch (final NumberFormatException e) {
        }
        return parsed;
    }

}
