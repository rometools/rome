package org.rometools.feed.module.sse.modules;

import com.sun.syndication.feed.CopyFrom;
import java.util.Date;

/**
 * <pre><sx:sharing></pre>Element within RSS <pre><channel></pre> or OPML <pre><head></pre>.
 */
public class Sharing extends SSEModule {
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
    private Date since;
    // date after which updated items are not included in the feed.

    // version of the sse in shared channel
    private String version;

    private Date until;
    private Related related;

    public void copyFrom(CopyFrom obj) {
        Sharing sharing = (Sharing)obj;
        ordered = sharing.ordered;
        since = sharing.since == null ? null : (Date) sharing.since.clone();
        window = sharing.window;
        until = sharing.until == null ? null : (Date) sharing.until.clone();
        version = sharing.version;
    }

    /**
     * ordered An optional, Boolean attribute. If present and its value is "true" (lower-case), subscribers MUST treat
     * the item list as an ordered set (see section 3.2). If this attribute is omitted or blank, it is assumed that this
     * is an unordered feed.
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
    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    /**
     * Provides an Integer that expresses the size of the window of change history kept by the publisher. Subscribers
     * MAY use this value to determine the frequency with which they must read a feed.
     *
     * @return an Integer that expresses the size of the window of change history kept by the publisher.
     */
    public Integer getWindow() {
        return window;
    }

    /**
     * Set an Integer that expresses the size of the window of change history kept by the publisher.
     *
     * @param window an Integer that expresses the size of the window of change history kept by the publisher.
     */
    public void setWindow(Integer window) {
        this.window = window;
    }

    /**
     * since An optional date-time attribute. All items updated on or after this date-time are included in the feed. If
     * not present or null, the "beginning of time" is assumed and the feed contains the node's complete item set as of
     * the until date-time.
     *
     * @return An optional date-time attribute.
     */
    public Date getSince() {
        return since;
    }

    /**
     * Sets the optional date-time attribute where all items updated on or after this date-time are included in the
     * feed.
     *
     * @param since An optional date-time attribute.
     */
    public void setSince(Date since) {
        this.since = since;
    }

    /**
     * until An optional date-time attribute. Items updated after this date are not included in the feed. The publisher
     * must guarantee that the value of until will increase if any items in the feed are updated. If this attribute is
     * omitted or blank, the subscriber cannot make assumptions about when the feed was updated.
     *
     * @return the date where items updated after this date are not included in the feed.
     */
    public Date getUntil() {
        return until;
    }

    /**
     * Set the date where items updated after this date are not included in the feed.
     *
     * @param until the date where items updated after this date are not included in the feed.
     */
    public void setUntil(Date until) {
        this.until = until;
    }

    public void setRelated(Related related) {
        this.related = related;
    }

    public Related getRelated() {
        return related;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getVersion() {
        return version;
    }
}
