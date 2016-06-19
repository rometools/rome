/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 The ROME Team
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

import com.rometools.rome.feed.module.Extendable;

/**
 * Bean interface for authors and contributors of SyndFeedImpl feeds and entries.
 */
public interface SyndPerson extends Cloneable, Extendable {

    /**
     * Returns name of person
     */
    public String getName();

    /**
     * Sets name of person.
     */
    public void setName(String name);

    /**
     * Returns URI of person.
     */
    public String getUri();

    /**
     * Sets URI of person.
     */
    public void setUri(String uri);

    /**
     * Returns email of person.
     */
    public String getEmail();

    /**
     * Sets email of person.
     */
    public void setEmail(String email);

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
