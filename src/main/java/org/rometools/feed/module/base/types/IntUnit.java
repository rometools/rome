/*
 * IntUnit.java
 *
 * Created on November 16, 2005, 12:49 PM
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rometools.feed.module.base.types;

import org.rometools.feed.module.base.io.GoogleBaseParser;

/**
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.1 $
 */
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

    /** Creates a new instance of IntUnit */
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
