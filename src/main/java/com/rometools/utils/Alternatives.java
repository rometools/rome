package com.rometools.utils;

public final class Alternatives {

    private Alternatives() {
    }

    /**
     * Returns the first object that is not null
     *
     * @param objects The objects to process
     * @return The first value that is not null. null when there is no not-null value
     */
    public static <T> T firstNotNull(final T... objects) {
        for (final T object : objects) {
            if (object != null) {
                return object;
            }
        }
        return null;
    }

}
