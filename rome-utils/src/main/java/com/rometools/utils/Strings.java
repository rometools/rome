/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.utils;

import java.util.Locale;

public final class Strings {

    private Strings() {
    }

    /**
     * Checks whether a String is null.
     *
     * @param s The String to check
     * @return true when the String is null, false otherwise
     */
    public static boolean isNull(final String s) {
        return s == null;
    }

    /**
     * Checks whether a String is null or empty.
     *
     * @param s The String to check
     * @return true when the String is null or empty, false otherwise
     */
    public static boolean isEmpty(final String s) {
        return isNull(s) || s.isEmpty();
    }

    /**
     * Checks whether a String is neither null nor empty.
     *
     * @param s The String to check
     * @return true when the String is neither null nor empty, false otherwise
     */
    public static boolean isNotEmpty(final String s) {
        return !isEmpty(s);
    }

    /**
     * Checks whether a String is null, empty or blank.
     *
     * @param s The String to check
     * @return true when the String is null, empty or blank, false otherwise
     */
    public static boolean isBlank(final String s) {
        return isEmpty(s) || s.trim().isEmpty();
    }

    /**
     * Removes the whitespace at the beginning and end of a String.
     *
     * @param s The String to trim, may be null
     * @return null when the input String is null, the trimmed String otherwise
     */
    public static String trim(final String s) {
        if (s == null) {
            return null;
        } else {
            return s.trim();
        }
    }

    /**
     * Removes the whitespace at the beginning and end of a String. When the String only contains
     * whitespace, it returns null.
     *
     * @param s The String to trim, may be null
     * @return null when the input String is null or does only contain whitespace, the trimmed
     *         String otherwise
     */
    public static String trimToNull(final String s) {
        final String trimmed = trim(s);
        if (trimmed == null || trimmed.isEmpty()) {
            return null;
        } else {
            return trimmed;
        }
    }

    /**
     * Removes the whitespace at the beginning and end of a String. When the String only contains
     * whitespace, it returns null.
     *
     * @param s The String to trim, may be null
     * @return null when the input String is null or does only contain whitespace, the trimmed
     *         String otherwise
     */
    public static String trimToEmpty(final String s) {
        final String trimmed = trim(s);
        if (trimmed == null || trimmed.isEmpty()) {
            return "";
        } else {
            return trimmed;
        }
    }

    /**
     * null-safe lower-case conversion of a String.
     *
     * @param s The String to process, may be null
     * @return null when the input String is null, the String in lower-case otherwise
     */
    public static String toLowerCase(final String s) {
        if (s == null) {
            return null;
        } else {
            return s.toLowerCase(Locale.ENGLISH);
        }
    }

}
