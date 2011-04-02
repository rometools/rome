package org.rometools.feed.module.sse.modules;

import com.sun.syndication.feed.CopyFrom;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre><sx:sync></pre>Element within RSS <pre><item></pre> or OPML <pre><outline></pre>.
 */
public class Sync extends SSEModule {
    public static final String NAME = "sync";

    public static final String ID_ATTRIBUTE = "id";
    public static final String VERSION_ATTRIBUTE = "version";
    public static final String CONFLICT_ATTRIBUTE = "conflict";
    public static final String DELETED_ATTRIBUTE = "deleted";

    // item identifier
    private String id;
    // item sequence modification number
    private Integer version;
    // indication of whether the item is deleted and is a tombstone
    private Boolean deleted;
    // an indication of whether there was an update conflict
    private Boolean conflict;

    private History history;
    private List conflicts;

    public void copyFrom(CopyFrom obj) {
        Sync sync = (Sync)obj;
        deleted = sync.deleted;
        version = sync.version;
        conflict = sync.conflict;
        id = sync.id;
        history = (sync.history == null ? null : (History)sync.history.clone());
        if (sync.conflicts != null) {
            conflicts = new ArrayList();
            conflicts.addAll(sync.conflicts);
        }
    }

    /**
     * Provides access to the sync id, a required, string attribute. This is the identifier for the item.
     * <p/>
     * The ID is assigned by the creator of the item, and MUST NOT be changed by subsequent publishers. Applications
     * will collate and compare these identifiers, therefore they MUST conform to the syntax for Namespace Specific
     * Strings (the NSS portion of a URN) in RFC 2141.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the identifier for the item. The ID MUST be globally unique within the feed and it MUST be identical across
     * feeds if an item is being shared or replicated as part of multiple distinct independent feeds.
     *
     * @param id the identifier for the item.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Provides access to a required, integer attribute. This is the modification sequence number of the item, starting
     * at 1 and incrementing by 1 indefinitely for each subsequent modification.
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Set the modification sequence number of the item.
     *
     * @param version the modification sequence number of the item.
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Provide access to an optional, Boolean attribute. If present and its value is "true" (lower-case), it indicates
     * that the item has been deleted and this is a tombstone. If not present, or if present with value of "false" or
     * "", then the item is not deleted. All other values are invalid.
     */
    public Boolean isDeleted() {
        return deleted;
    }

    /**
     * Set an indication of whether this item has been deleted and is a tombstone.
     *
     * @param deleted an indication of whether this item has been deleted and is a tombstone.
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Provides access to an optional, Boolean conflict attribute. If present and its value is "true" (lower-case), it
     * indicates there was an update conflict detected when processing an update of this item, and it should potentially
     * be examined by the user. If not present, or present with value of "false" or "", Then no conflict has been
     * detected. All other values are invalid.
     *
     * @return indicates there was an update conflict detected when processing an update of this item.
     */
    public Boolean isConflict() {
        return conflict;
    }

    /**
     * Set an indication of whether there was an update conflict detected when processing an update of this item.
     *
     * @param conflict an indication of whether there was an update conflict detected when processing an update of this
     *                 item.
     */
    public void setConflict(Boolean conflict) {
        this.conflict = conflict;
    }

    // TODO: does it make sense for the sync element to have a history?
    // TODO: should the history be a module?

    /**
     * The history history for this sync object
     *
     * @param history the history for this sync object.
     */
    public void setHistory(History history) {
        this.history = history;
    }

    /**
     * Get the history for this sync object
     *
     * @return get the history for this sync object.
     */
    public History getHistory() {
        return history;
    }

    public void addConflict(Conflict conflict) {
        if (conflicts == null) {
            conflicts = new ArrayList();
        }
        conflicts.add(conflict);
    }

    public List getConflicts() {
        return conflicts;
    }

    public void setConflicts(List conflicts) {
        this.conflicts = conflicts;
    }
}
