package org.rometools.feed.module.sse.modules;

import com.sun.syndication.feed.CopyFrom;
import java.util.Date;

/**
 * <pre><sx:update></pre>Element within <pre><sx:history></pre>.
 */
public class Update extends SSEModule {
    public static final String NAME = "update";
    public static final String BY_ATTRIBUTE = "by";
    public static final String WHEN_ATTRIBUTE = "when";

    private Date when;
    private String by;

    public void copyFrom(CopyFrom other) {
        Update otherUpdate = (Update)other;
        otherUpdate.when = when == null ? null : (Date) when.clone();
        // dont copy immutable
        otherUpdate.by = by;
    }

    /**
     * Provides access to the date-time when the modification took place. If this attribute is omitted
     * the value defaults to the earliest time representable in RFC 822. Either or both of the when or by attributes
     * MUST be present; it is invalid to have neither.
     */
    public Date getWhen() {
        return when;
    }

    /**
     * Set the date-time when the modification took place.
     *
     * @param when the date-time when the modification took place.
     */
    public void setWhen(Date when) {
        this.when = when;
    }

    /**
     * Provides access to a text attribute identifying the unique endpoint that made a modification. This SHOULD be
     * some combination of user and device (so that a given user can edit a feed on multiple devices). This attribute is
     * used programmatically to break ties in case two changes happened at the same time (within the same second).
     * Either or both of the when or by must be present; it is invalid to have neither.
     *
     * @return access to a text attribute identifying the unique endpoint that made a modification.
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets a text attribute identifying the unique endpoint that made a modification.
     *
     * @param by a text attribute identifying the unique endpoint that made a modification. 
     */
    public void setBy(String by) {
        this.by = by;
    }
}
