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
 * <sx:related>
 * </pre>
 *
 * Element within
 *
 * <pre>
 * <sx:sharing>
 * </pre>
 *
 * .
 */
public class Related extends SSEModule {
    private static final long serialVersionUID = 1L;

    public static final String NAME = "related";

    /**
     * Indicates whether the link points to a file containing the complete collection of items for
     * this feed.
     */
    public static final int COMPLETE = 0;

    /**
     * Indicates whether the link points to a feed whose contents are being incorporated into this
     * feed by the publisher.
     */
    public static final int AGGREGATED = 1;

    // url for related feeds
    private String link;
    // name or description of the related feed
    private String title;
    // the type of the relation "complete" or "aggregated"
    private Integer type;
    // starting point of the related feed
    private LocalDateTime since;
    // ending point of a feed
    private LocalDateTime until;

    public static final String LINK_ATTRIBUTE = "link";
    public static final String SINCE_ATTRIBUTE = "since";
    public static final String TITLE_ATTRIBUTE = "title";
    public static final String TYPE_ATTRIBUTE = "type";
    public static final String UNTIL_ATTRIBUTE = "until";

    @Override
    public void copyFrom(final CopyFrom obj) {
        final Related related = (Related) obj;
        related.link = link;
        related.since = since == null ? null : since;
        related.title = title;
        related.type = type;
        related.until = until == null ? null : until;
    }

    /**
     * link A required, URL attribute. The URL for related feeds.
     *
     * @return the URL for related feeds
     */
    // TODO: use a java.net.URL?
    public String getLink() {
        return link;
    }

    /**
     * Set the URL for related feeds.
     *
     * @param link the URL for related feeds.
     */
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * title An optional, string attribute. The name or description of the related feed.
     *
     * @return The name or description of the related feed.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the name or description of the related feed.
     *
     * @param title the name or description of the related feed.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * type A required, string attribute. This attribute can have one of the following values:
     * <p>
     * "complete" if the link points to file containing the complete collection of items for this
     * feed.
     * <p>
     * "aggregated" if the link points to a feed whose contents are being incorporated into this
     * feed by the publisher.
     *
     * @return the type of the releated feed.
     */
    public Integer getType() {
        return type;
    }

    /**
     * Set the type of relationship, complete or aggregated.
     *
     * @param type the type of relationship, complete or aggregated.
     */
    public void setType(final Integer type) {
        this.type = type;
    }

    /**
     * An optional, date-time attribute. This is the starting point of the related feed. If this
     * attribute is omitted or blank, it is assumed that this is a complete feed.
     *
     * @return the starting point of the related feed.
     */
    public LocalDateTime getSince() {
        return since;
    }

    /**
     * Set the starting point of the related feed.
     *
     * @param since the starting point of the related feed.
     */
    public void setSince(final LocalDateTime since) {
        this.since = since;
    }

    /**
     * An optional, date-time attribute. This is the ending point of a feed.
     *
     * @return the ending point of the feed, until.
     */
    public LocalDateTime getUntil() {
        return until;
    }

    /**
     * Set the ending point of the feed, until. An optional, date-time attribute.
     *
     * @param until the ending point of the feed.
     */
    public void setUntil(final LocalDateTime until) {
        this.until = until;
    }
}
