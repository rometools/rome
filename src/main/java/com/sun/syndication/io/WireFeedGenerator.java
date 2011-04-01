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
package com.sun.syndication.io;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.io.FeedException;
import org.jdom.Document;

/**
 * Generates an XML document (JDOM) out of a feed for a specific real feed type.
 * <p>
 * WireFeedGenerator instances must thread safe.
 * <p>
 * TODO: explain how developers can plugin their own implementations.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface WireFeedGenerator {

    /**
     * Returns the type of feed the generator creates.
     * <p>
     * @see WireFeed for details on the format of this string.
     * <p>
     * @return the type of feed the generator creates.
     *
     */
    public String getType();

    /**
     * Creates an XML document (JDOM) for the given feed bean.
     * <p>
     * @param feed the feed bean to generate the XML document from.
     * @return the generated XML document (JDOM).
     * @throws IllegalArgumentException thrown if the type of the given feed bean does not
     *         match with the type of the WireFeedGenerator.
     * @throws FeedException thrown if the XML Document could not be created.
     *
     */
    public Document generate(WireFeed feed) throws IllegalArgumentException,FeedException;

}
