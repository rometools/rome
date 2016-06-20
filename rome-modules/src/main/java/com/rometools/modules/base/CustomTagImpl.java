/*
 * Copyright 2006 Robert Cooper, Temple of the Screaming Penguin
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
package com.rometools.modules.base;

import java.net.URL;
import java.util.Date;

import com.rometools.modules.base.types.DateTimeRange;
import com.rometools.modules.base.types.FloatUnit;
import com.rometools.modules.base.types.IntUnit;
import com.rometools.modules.base.types.ShortDate;

public class CustomTagImpl implements CustomTag {
    private Object value;
    private final String name;

    public CustomTagImpl(final String name, final String value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Integer value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Float value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final IntUnit value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final FloatUnit value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final ShortDate value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Date value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final DateTimeRange value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final URL value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Boolean value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    public CustomTagImpl(final String name, final Location value) {
        if (name == null || value == null) {
            throw new NullPointerException("Name and Value cannont be null.");
        }
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof CustomTag) {
            final CustomTag tag = (CustomTag) o;
            if (name.equals(tag.getName()) && value.equals(tag.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final CustomTagImpl cti = new CustomTagImpl(name, "");
        cti.value = value;
        return cti;
    }

    @Override
    public String toString() {
        return "[custom name=\"" + name + "\" value=\"" + value.toString() + "\"]";
    }

    public static class Location {
        private final String value;

        public Location(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public Object clone() {
            return new Location(value);
        }

        @Override
        public String toString() {
            return value;
        }

        @Override
        public boolean equals(final Object o) {
            if (o instanceof Location && ((Location) o).value.equals(value)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
