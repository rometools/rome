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
package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Interface that defines the functionality to convert a SyndFeedImpl
 * to a real feed (RSS or Atom) and vice versa.
 * <p>
 * Each implementation knows how to deal with a specific type (version)
 * of a real feed.
 * <p>
 * Implementations must be thread safe.
 * <p>
 * TODO: explain how developers can plugin their own implementations.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface Converter {

    /**
     * Returns the type (version) of the real feed this converter handles.
     * <p>
     * @see WireFeed for details on the format of this string.
     * <p>
     * @return the real feed type.
     *
     */
    public String getType();

    /**
     * Makes a deep copy/conversion of the values of a real feed into a SyndFeedImpl.
     * <p>
     * It assumes the given SyndFeedImpl has no properties set.
     * <p>
     * @param feed real feed to copy/convert.
     * @param syndFeed the SyndFeedImpl that will contain the copied/converted values of the real feed.
     *
     */
    public void copyInto(WireFeed feed,SyndFeed syndFeed);

    /**
     * Creates real feed with a deep copy/conversion of the values of a SyndFeedImpl.
     * <p>
     * @param syndFeed SyndFeedImpl to copy/convert value from.
     * @return a real feed with copied/converted values of the SyndFeedImpl.
     *
     */
    public WireFeed createRealFeed(SyndFeed syndFeed);

}
