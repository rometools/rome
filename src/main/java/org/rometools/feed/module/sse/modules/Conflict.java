package org.rometools.feed.module.sse.modules;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.rss.Item;

import java.util.Date;

/**
 * <sx:conflict> element within <sx:conflicts>
 */
public class Conflict extends SSEModule {
    public static final String CONFLICTS_NAME = "conflicts";

    public static final String NAME = "conflict";

    /**
     * An optional, string attribute. This text attribute identifies the endpoint that
     * made the conflicting modification. It is used and compared programmatically.
     * See sx:update for format guidance.
     * <p>
     * Note: Either or both of the when or by attributes MUST be present; it is
     * invalid to have neither.
     */
    public static final String BY_ATTRIBUTE = "by";

    /**
     * A required, integer attribute. This is the version number of the conflicting
     * modification.
     */
    public static final String VERSION_ATTRIBUTE = "version";

    /**
     * An optional, date-time attribute. This is the date-time when the conflicting
     * modification took place. See sx:update for format guidance.
     * <p>
     * Note: Either or both of the when or by attributes MUST be present; it is
     * invalid to have neither.
     */
    public static final String WHEN_ATTRIBUTE = "when";

    private Integer version;
    private Date when;
    private String by;
    private Item conflictItem;

    public void copyFrom(CopyFrom obj) {
        Conflict conflict = (Conflict)obj;
        conflict.when = when == null ? null : (Date) when.clone();
        conflict.by = by;
        conflict.version = version;
        try {
            conflict.conflictItem = (Item) conflictItem.clone();
        } catch (CloneNotSupportedException e) {
            // should not happen
            e.printStackTrace();
        }
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public void setItem(Item conflictItem) {
        this.conflictItem = conflictItem;
    }

    public Item getItem() {
        return conflictItem;
    }
}
