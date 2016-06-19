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

/**
 * Enumeration of values suitable for "price_type" or "salary_type".
 */
public class PriceTypeEnumeration implements CloneableType {
    /**
     * Indicates the value is a starting value.
     */
    public static final PriceTypeEnumeration STARTING = new PriceTypeEnumeration("starting");
    /**
     * Indicates the value is negotiable
     */
    public static final PriceTypeEnumeration NEGOTIABLE = new PriceTypeEnumeration("negotiable");
    /**
     * String value encapsulated
     */
    private final String value;

    /**
     * @param value Value to encapsulate
     */
    private PriceTypeEnumeration(final String value) {
        this.value = value;
    }

    /**
     * Returns the string value of this instance.
     *
     * @return Returns the string value of this instance.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns a PriceTypeEnumeration based on the String value or null.
     *
     * @param value Value to search for.
     * @return PriceTypeEnumeration or null.
     */
    public static PriceTypeEnumeration findByValue(final String value) {
        if (value.equalsIgnoreCase("negotiable")) {
            return PriceTypeEnumeration.NEGOTIABLE;
        } else {
            return PriceTypeEnumeration.STARTING;
        }
    }

    /**
     * Returns a duplicate of this instance
     *
     * @return The same instance.
     */
    @Override
    public Object clone() {
        return this;
    }

    /**
     * Returns the string value of this instance.
     *
     * @return Returns the string value of this instance.
     */
    @Override
    public String toString() {
        return value;
    }
}
