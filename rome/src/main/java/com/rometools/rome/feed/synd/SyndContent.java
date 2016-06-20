/*
 * Copyright 2004 Sun Microsystems, Inc.
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
 *
 */
package com.rometools.rome.feed.synd;

import com.rometools.rome.feed.CopyFrom;

/**
 * Bean interface for content of SyndFeedImpl entries.
 */
public interface SyndContent extends Cloneable, CopyFrom {
    /**
     * Returns the content type.
     * <p>
     * When used for the description of an entry, if <b>null</b> 'text/plain' must be assumed.
     * <p>
     *
     * @return the content type, <b>null</b> if none.
     *
     */
    String getType();

    /**
     * Sets the content type.
     * <p>
     * When used for the description of an entry, if <b>null</b> 'text/plain' must be assumed.
     * <p>
     *
     * @param type the content type to set, <b>null</b> if none.
     *
     */
    void setType(String type);

    /**
     * Gets the content mode (needed for Atom 0.3 support).
     *
     * @return type the content, <b>null</b> if none.
     *
     */
    String getMode();

    /**
     * Sets the content mode (needed for Atom 0.3 support).
     *
     * @param mode the content mode to set, <b>null</b> if none.
     *
     */
    void setMode(String mode);

    /**
     * Returns the content value.
     * <p>
     *
     * @return the content value, <b>null</b> if none.
     *
     */
    String getValue();

    /**
     * Sets the content value.
     * <p>
     *
     * @param value the content value to set, <b>null</b> if none.
     *
     */
    void setValue(String value);

    /**
     * Creates a deep clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException;

}
