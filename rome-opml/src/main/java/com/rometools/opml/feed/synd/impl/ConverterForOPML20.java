/*
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
package com.rometools.opml.feed.synd.impl;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;

public class ConverterForOPML20 extends ConverterForOPML10 {

    /**
     * Returns the type (version) of the real feed this converter handles.
     * <p>
     *
     * @return the real feed type.
     * @see WireFeed for details on the format of this string.
     *      <p>
     */
    @Override
    public String getType() {
        return "opml_2.0";
    }

    /**
     * Makes a deep copy/conversion of the values of a real feed into a SyndFeedImpl.
     * <p>
     * It assumes the given SyndFeedImpl has no properties set.
     * <p>
     *
     * @param feed real feed to copy/convert.
     * @param syndFeed the SyndFeedImpl that will contain the copied/converted values of the real feed.
     */
    @Override
    public void copyInto(final WireFeed feed, final SyndFeed syndFeed) {
        super.copyInto(feed, syndFeed);
    }

    /**
     * Creates real feed with a deep copy/conversion of the values of a SyndFeedImpl.
     * <p>
     *
     * @param syndFeed SyndFeedImpl to copy/convert value from.
     * @return a real feed with copied/converted values of the SyndFeedImpl.
     */
    @Override
    public WireFeed createRealFeed(final SyndFeed syndFeed) {
        return super.createRealFeed(syndFeed);
    }

}
