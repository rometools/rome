package com.rometools.utils;

public final class Integers {

    private Integers() {
    }

    /**
     * Converts a String into an Integer.
     * 
     * @param s The String to convert, may be null
     * @return The parsed Integer or null when parsing is not possible
     */
    public static Integer parse(final String s) {
        try {
            return Integer.parseInt(s);
        } catch (final NumberFormatException e) {
            return null;
        }
    }

}
