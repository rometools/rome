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

import java.util.Calendar;
import java.util.Date;

/**
 * This class represents a simple 4 digit year.
 */
public class YearType implements CloneableType {
    /**
     * year value
     */
    int year;

    /**
     * Creates a new year from a string value.
     *
     * @param year String to parse.
     */
    public YearType(final String year) {
        this.year = Integer.parseInt(year.trim());
    }

    /**
     * @param date Date to get the year from.
     */
    public YearType(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = cal.get(Calendar.YEAR);
    }

    /**
     * Duplicates this object.
     *
     * @return Cloned Year.
     */
    @Override
    public Object clone() {
        return new YearType(Integer.toString(year));
    }

    /**
     * Returns a String representation of this object.
     *
     * @return Returns a String representation of this object.
     */
    @Override
    public String toString() {
        return Integer.toString(year);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof YearType)) {
            return false;
        }
        if (toString().equals(o.toString())) {
            return true;
        }
        return false;
    }
}
