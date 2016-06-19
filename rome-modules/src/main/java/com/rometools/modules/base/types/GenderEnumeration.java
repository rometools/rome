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
 * Simple enumeration for Genders.
 */
public class GenderEnumeration implements CloneableType {
    /** Men */
    public static final GenderEnumeration MALE = new GenderEnumeration("Male");
    /** Women */
    public static final GenderEnumeration FEMALE = new GenderEnumeration("Female");
    private final String value;

    private GenderEnumeration(final String value) {
        this.value = value;
    }

    /** Returns the proper instance based on the string value */
    public static GenderEnumeration findByValue(final String value) {
        if (value == null) {
            return null;
        }

        final String gender = value.toUpperCase();

        if (gender.charAt(0) == 'M') {
            return GenderEnumeration.MALE;
        } else if (gender.charAt(0) == 'F') {
            return GenderEnumeration.FEMALE;
        } else {
            return null;
        }
    }

    /** Returns the value of the instance */
    public String getValue() {
        return value;
    }

    /** Returns a reference to the same object. :P */
    @Override
    public Object clone() {
        return this;
    }

    @Override
    public String toString() {
        return value;
    }
}
