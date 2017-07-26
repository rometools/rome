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

import java.util.StringTokenizer;

/**
 * Represents the size on an item in 2 or 3 dimensions.
 */
public class Size implements CloneableType {
    /**
     * height
     */
    private FloatUnit height;
    /**
     * length
     */
    private final FloatUnit length;
    /**
     * width
     */
    private final FloatUnit width;

    /**
     * Creates a new Size object parsing a string value.
     *
     * @param source String value to parse
     */
    public Size(final String source) {
        final StringTokenizer tok = new StringTokenizer(source, "Xx");
        length = new FloatUnit(tok.nextToken());
        width = new FloatUnit(tok.nextToken());

        if (tok.hasMoreTokens()) {
            height = new FloatUnit(tok.nextToken());
        }
    }

    /**
     * @param length lenght value
     * @param width width value
     */
    public Size(final FloatUnit length, final FloatUnit width) {
        this.length = length;
        this.width = width;
    }

    /**
     * @param length Length value.
     * @param width Width value.
     * @param height Height value.
     */
    public Size(final FloatUnit length, final FloatUnit width, final FloatUnit height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /**
     * Height value.
     *
     * @return Height value.
     */
    public FloatUnit getHeight() {
        return height;
    }

    /**
     * Length value.
     *
     * @return Length value.
     */
    public FloatUnit getLength() {
        return length;
    }

    /**
     * Width value.
     *
     * @return Width value.
     */
    public FloatUnit getWidth() {
        return width;
    }

    /**
     * Duplicates this object.
     *
     * @return A duplicate Size object.
     */
    @Override
    public Object clone() {
        if (height != null) {
            return new Size(length, width, height);
        } else {
            return new Size(length, width);
        }
    }

    /**
     * Returns a string representation of this object.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        if (height != null) {
            return length + "x" + width + "x" + height;
        } else {
            return length + "x" + width;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Size)) {
            return false;
        }
        if (toString().equals(o.toString())) {
            return true;
        }
        return false;
    }
}
