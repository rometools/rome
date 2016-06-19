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

import java.util.Date;

/**
 * This is a simple wrapper for java.util.Date that indicates it should be formatted without time of
 * day for Google Base. It should be transparent to module developers.
 *
 * Move along. Nothing to see here.
 */
public class ShortDate extends Date implements CloneableType {

    private static final long serialVersionUID = 1L;

    public ShortDate() {
        super();
    }

    /**
     * Creates a new Short Date based on a Date value.
     *
     * @param date Date value to read from.
     */
    public ShortDate(final Date date) {
        super(date.getTime());
    }

    /**
     * Creates a new ShortDate based on a millisecond time.
     *
     * @param time Millisecond time to start with.
     */
    public ShortDate(final long time) {
        super(time);
    }

    /**
     * Returns a duplicate of this object.
     *
     * @return Duplicate of the object.
     */
    @Override
    public Object clone() {
        return new ShortDate(getTime());
    }
}
