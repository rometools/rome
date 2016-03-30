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
package com.rometools.fetcher;

import java.util.EventObject;

import com.rometools.rome.feed.synd.SyndFeed;

/**
 * Implementation note: FetcherEvent is not thread safe. Make sure that they are only ever accessed
 * by one thread. If necessary, make all getters and setters synchronized, or alternatively make all
 * fields final.
 *
 * @author nl
 * 
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public class FetcherEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public static final String EVENT_TYPE_FEED_POLLED = "FEED_POLLED";
    public static final String EVENT_TYPE_FEED_RETRIEVED = "FEED_RETRIEVED";
    public static final String EVENT_TYPE_FEED_UNCHANGED = "FEED_UNCHANGED";

    private String eventType;
    private String urlString;
    private SyndFeed feed;

    public FetcherEvent(final Object source) {
        super(source);
    }

    public FetcherEvent(final Object source, final String urlStr, final String eventType) {
        this(source);
        setUrlString(urlStr);
        setEventType(eventType);
    }

    public FetcherEvent(final Object source, final String urlStr, final String eventType, final SyndFeed feed) {
        this(source, urlStr, eventType);
        setFeed(feed);
    }

    /**
     * @return Returns the feed.
     *
     *         <p>
     *         The feed will only be set if the eventType is EVENT_TYPE_FEED_RETRIEVED
     *         </p>
     */
    public SyndFeed getFeed() {
        return feed;
    }

    /**
     * @param feed The feed to set.
     *
     *            <p>
     *            The feed will only be set if the eventType is EVENT_TYPE_FEED_RETRIEVED
     *            </p>
     */
    public void setFeed(final SyndFeed feed) {
        this.feed = feed;
    }

    /**
     * @return Returns the eventType.
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType The eventType to set.
     */
    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return Returns the urlString.
     */
    public String getUrlString() {
        return urlString;
    }

    /**
     * @param urlString The urlString to set.
     */
    public void setUrlString(final String urlString) {
        this.urlString = urlString;
    }
}
