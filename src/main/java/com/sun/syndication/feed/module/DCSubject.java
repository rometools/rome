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
package com.sun.syndication.feed.module;

import com.sun.syndication.feed.CopyFrom;

/**
 * Subject of the Dublin Core ModuleImpl.
 * <p>
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core module</a>.
 * @author Alejandro Abdelnur
 *
 */
public interface DCSubject extends Cloneable,CopyFrom {
    /**
     * Returns the DublinCore subject taxonomy URI.
     * <p>
     * @return the DublinCore subject taxonomy URI, <b>null</b> if none.
     *
     */
    String getTaxonomyUri();

    /**
     * Sets the DublinCore subject taxonomy URI.
     * <p>
     * @param taxonomyUri the DublinCore subject taxonomy URI to set, <b>null</b> if none.
     *
     */
    void setTaxonomyUri(String taxonomyUri);

    /**
     * Returns the DublinCore subject value.
     * <p>
     * @return the DublinCore subject value, <b>null</b> if none.
     *
     */
    String getValue();

    /**
     * Sets the DublinCore subject value.
     * <p>
     * @param value the DublinCore subject value to set, <b>null</b> if none.
     *
     */
    void setValue(String value);

    /**
     * Creates a deep clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException;

}
