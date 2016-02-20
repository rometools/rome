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
