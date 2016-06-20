/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
 */
package com.rometools.modules.base.types;

import com.rometools.modules.base.io.GoogleBaseParser;

public class IntUnit implements CloneableType {
    private String units;
    private final int value;

    private boolean inCharArray(final char find, final char[] array) {
        for (final char element : array) {
            if (find == element) {
                return true;
            }
        }
        return false;
    }

    public IntUnit(final String source) {
        final String parse = source.trim();
        int space = -1;
        for (int i = 0; i < parse.length(); i++) {
            if (!inCharArray(parse.charAt(i), GoogleBaseParser.INTEGER_CHARS)) {
                space = i;
                break;
            }
        }
        if (space == -1) {
            space = parse.length();
        }

        value = Integer.parseInt(GoogleBaseParser.stripNonValidCharacters(GoogleBaseParser.INTEGER_CHARS, parse.substring(0, space)));

        if (space != parse.length()) {
            units = parse.substring(space, parse.length()).trim();
        }
    }

    public IntUnit(final int value, final String units) {
        this.value = value;
        this.units = units;
    }

    public String getUnits() {
        return units;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Object clone() {
        return new IntUnit(value, units);
    }

    @Override
    public String toString() {
        if (units != null && units.trim().length() > 0) {
            return value + " " + units;
        } else {
            return Integer.toString(value);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IntUnit)) {
            return false;
        }
        final IntUnit f = (IntUnit) o;
        if (f.getValue() != value) {
            return false;
        }
        if (units == f.getUnits() || units != null && units.equals(f.getUnits())) {
            return true;
        }
        return false;
    }
}
