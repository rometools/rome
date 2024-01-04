/*
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
package com.rometools.modules.sse.modules;

import com.rometools.rome.feed.CopyFrom;

import java.time.LocalDateTime;

/**
 * <pre>
 * <sx:sharing>
 * </pre>
 *
 * Element within RSS
 *
 * <pre>
 * <channel>
 * </pre>
 *
 * or OPML
 *
 * <pre>
 * <head>
 * </pre>
 *
 * .
 */
public class Sharing extends SSEModule {
    private static final long serialVersionUID = 1L;

    public static final String NAME = "sharing";

    public static final String UNTIL_ATTRIBUTE = "until";
    public static final String SINCE_ATTRIBUTE = "since";
    public static final String ORDERED_ATTRIBUTE = "ordered";
    public static final String WINDOW_ATTRIBUTE = "window";
    public static final String VERSION_ATTRIBUTE = "version";

    public static final String VERSION = "0.91";

    // whether subscribers MUST treat the item list as an ordered set
    private Boolean ordered;
    // expresses size of the window of change history kept by the published.
    private Integer window;
    // date after which updated items are included in the feed.
    private LocalDateTime since;
    // date after which updated items are not included in the feed.

    // version of the sse in shared channel
    private String version;

    private LocalDateTime until;
    private Related related;

    @Override
    public void copyFrom(final CopyFrom obj) {
        final Sharing sharing = (Sharing) obj;
        ordered = sharing.ordered;
        since = sharing.since == null ? null : sharing.since;
        window = sharing.window;
        until = sharing.until == null ? null : sharing.until;
        version = sharing.version;
    }

    /**
     * ordered An optional, Boolean attribute. If present and its value is "true" (lower-case),
     * subscribers MUST treat the item list as an ordered set (see section 3.2). If this attribute
     * is omitted or blank, it is assumed that this is an unordered feed.
     *
     * @return a Boolean indicating if subscribers must treat the item list as an ordered set.
     */
    public Boolean getOrdered() {
        return ordered;
    }

    /**
     * Set whether subscribers MUST tread the item list as an ordered set.
     *
     * @param ordered whether subscribers MUST tread the item list as an ordered set.
     */
    public void setOrdered(final Boolean ordered) {
        this.ordered = ordered;
    }

    /**
     * Provides an Integer that expresses the size of the window of change history kept by the
     * publisher. Subscribers MAY use this value to determine the frequency with which they must
     * read a feed.
     *
     * @return an Integer that expresses the size of the window of change history kept by the
     *         publisher.
     */
    public Integer getWindow() {
        return window;
    }

    /**
     * Set an Integer that expresses the size of the window of change history kept by the publisher.
     *
     * @param window an Integer that expresses the size of the window of change history kept by the
     *            publisher.
     */
    public void setWindow(final Integer window) {
        this.window = window;
    }

    /**
     * since An optional date-time attribute. All items updated on or after this date-time are
     * included in the feed. If not present or null, the "beginning of time" is assumed and the feed
     * contains the node's complete item set as of the until date-time.
     *
     * @return An optional date-time attribute.
     */
    public LocalDateTime getSince() {
        return since;
    }

    /**
     * Sets the optional date-time attribute where all items updated on or after this date-time are
     * included in the feed.
     *
     * @param since An optional date-time attribute.
     */
    public void setSince(final LocalDateTime since) {
        this.since = since;
    }

    /**
     * until An optional date-time attribute. Items updated after this date are not included in the
     * feed. The publisher must guarantee that the value of until will increase if any items in the
     * feed are updated. If this attribute is omitted or blank, the subscriber cannot make
     * assumptions about when the feed was updated.
     *
     * @return the date where items updated after this date are not included in the feed.
     */
    public LocalDateTime getUntil() {
        return until;
    }

    /**
     * Set the date where items updated after this date are not included in the feed.
     *
     * @param until the date where items updated after this date are not included in the feed.
     */
    public void setUntil(final LocalDateTime until) {
        this.until = until;
    }

    public void setRelated(final Related related) {
        this.related = related;
    }

    public Related getRelated() {
        return related;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public Object getVersion() {
        return version;
    }
}
