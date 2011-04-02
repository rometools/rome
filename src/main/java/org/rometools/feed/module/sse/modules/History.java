package org.rometools.feed.module.sse.modules;

import com.sun.syndication.feed.CopyFrom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre><sx:history></pre>Element within <pre><sx:sync></pre>.
 */
public class History extends SSEModule {
    // A date-time attribute.
    private Date when;

    // A string attribute.
    private String by;

    public static final String NAME = "history";

    public static final String WHEN_ATTRIBUTE = "when";
    public static final String BY_ATTRIBUTE = "by";

    private List updates;

    public History() {
    }

    public void copyFrom(CopyFrom other) {
        History otherHistory = (History)other;
        when = otherHistory.when == null ? null : (Date) otherHistory.when.clone();
        // dont copy immutable
        by = otherHistory.by;

        if (otherHistory.updates != null) {
            updates = new ArrayList();
            updates.addAll(otherHistory.updates);
        }
    }

    /**
     * Get the date-time when the most recent modification took place.
     * <p/>
     * This is the date-time when the most recent modification took place. If this attribute is omitted the value
     * defaults to the earliest time representable in RFC 822.
     *
     * @return the date-time when the most recent modification took place. 
     */
    public Date getWhen() {
        // TODO: convert to the earliest time in RFC 822 (which is what?)
        return when;
    }

    /**
     * Set the date-time when the most recent modification took place.
     * <p/>
     * Either or both of the when or by attributes MUST be present; it is invalid to have neither.
     *
     * @param when the date-time when the most recent modification took place.
     */
    public void setWhen(Date when) {
        this.when = when;
    }

    /**
     * Provides access to a text attribute identifying the unique endpoint that made the most recent modification. This
     * SHOULD be some combination of user and device (so that a given user can edit a feed on multiple devices). This
     * attribute is used programmatically to break ties in case two changes happened at the same time (within the same
     * second).
     * <p/>
     * Either or both of the when or by must be present; it is invalid to have neither.
     * <p/>
     * If this attribute is omitted the value defaults to the empty string (which must be less than all other values for
     * purposes of collation).
     *
     * @return A text attribute identifying the unique endpoint that made the most recent modification.
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the endpoint that made the most recent modification.
     * <p/>
     * Either or both of the when or by attributes MUST be present; it is invalid to have neither.
     *
     * @param by the endpoint that made the most recent modification.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Add an update to this history
     *
     * @param update an update to add to the list of updates for this history.
     */
    public void addUpdate(Update update) {
        if (updates == null) {
            updates = new ArrayList();
        }
        updates.add(update);
    }

    /**
     * Return the list of updates for this history.
     *
     * @return the list of updates for this history.
     */
    public List getUpdates() {
        return updates;
    }
}
