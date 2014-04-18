package com.rometools.utils;

public class Doubles {

    private Doubles() {
    }

    /**
     * Converts a String into an Double.
     *
     * @param s The String to convert, may be null
     * @return The parsed Double or null when parsing is not possible
     */
    public static Double parse(final String s) {
        Double parsed = null;
        try {
            if (s != null) {
                parsed = Double.parseDouble(s);
            }
        } catch (final NumberFormatException e) {
        }
        return parsed;
    }

}
