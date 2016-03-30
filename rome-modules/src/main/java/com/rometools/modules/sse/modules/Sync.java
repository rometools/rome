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
package com.rometools.modules.sse.modules;

import java.util.ArrayList;
import java.util.List;

import com.rometools.rome.feed.CopyFrom;

/**
 * <pre>
 * <sx:sync>
 * </pre>
 *
 * Element within RSS
 *
 * <pre>
 * <item>
 * </pre>
 *
 * or OPML
 *
 * <pre>
 * <outline>
 * </pre>
 *
 * .
 */
public class Sync extends SSEModule {
    private static final long serialVersionUID = 1L;

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
    private List<Conflict> conflicts;

    @Override
    public void copyFrom(final CopyFrom obj) {
        final Sync sync = (Sync) obj;
        deleted = sync.deleted;
        version = sync.version;
        conflict = sync.conflict;
        id = sync.id;
        history = sync.history == null ? null : (History) sync.history.clone();
        if (sync.conflicts != null) {
            conflicts = new ArrayList<Conflict>();
            conflicts.addAll(sync.conflicts);
        }
    }

    /**
     * Provides access to the sync id, a required, string attribute. This is the identifier for the
     * item.
     * <p/>
     * The ID is assigned by the creator of the item, and MUST NOT be changed by subsequent
     * publishers. Applications will collate and compare these identifiers, therefore they MUST
     * conform to the syntax for Namespace Specific Strings (the NSS portion of a URN) in RFC 2141.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the identifier for the item. The ID MUST be globally unique within the feed and it MUST
     * be identical across feeds if an item is being shared or replicated as part of multiple
     * distinct independent feeds.
     *
     * @param id the identifier for the item.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Provides access to a required, integer attribute. This is the modification sequence number of
     * the item, starting at 1 and incrementing by 1 indefinitely for each subsequent modification.
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Set the modification sequence number of the item.
     *
     * @param version the modification sequence number of the item.
     */
    public void setVersion(final Integer version) {
        this.version = version;
    }

    /**
     * Provide access to an optional, Boolean attribute. If present and its value is "true"
     * (lower-case), it indicates that the item has been deleted and this is a tombstone. If not
     * present, or if present with value of "false" or "", then the item is not deleted. All other
     * values are invalid.
     */
    public Boolean isDeleted() {
        return deleted;
    }

    /**
     * Set an indication of whether this item has been deleted and is a tombstone.
     *
     * @param deleted an indication of whether this item has been deleted and is a tombstone.
     */
    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Provides access to an optional, Boolean conflict attribute. If present and its value is
     * "true" (lower-case), it indicates there was an update conflict detected when processing an
     * update of this item, and it should potentially be examined by the user. If not present, or
     * present with value of "false" or "", Then no conflict has been detected. All other values are
     * invalid.
     *
     * @return indicates there was an update conflict detected when processing an update of this
     *         item.
     */
    public Boolean isConflict() {
        return conflict;
    }

    /**
     * Set an indication of whether there was an update conflict detected when processing an update
     * of this item.
     *
     * @param conflict an indication of whether there was an update conflict detected when
     *            processing an update of this item.
     */
    public void setConflict(final Boolean conflict) {
        this.conflict = conflict;
    }

    // TODO: does it make sense for the sync element to have a history?
    // TODO: should the history be a module?

    /**
     * The history history for this sync object
     *
     * @param history the history for this sync object.
     */
    public void setHistory(final History history) {
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

    public void addConflict(final Conflict conflict) {
        if (conflicts == null) {
            conflicts = new ArrayList<Conflict>();
        }
        conflicts.add(conflict);
    }

    public List<Conflict> getConflicts() {
        return conflicts;
    }

    public void setConflicts(final List<Conflict> conflicts) {
        this.conflicts = conflicts;
    }
}
