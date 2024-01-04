/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.rometools.modules.sse.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.rss.Item;

import java.time.LocalDateTime;

/**
 * <sx:conflict> element within <sx:conflicts>
 */
public class Conflict extends SSEModule {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(Conflict.class);

    public static final String CONFLICTS_NAME = "conflicts";

    public static final String NAME = "conflict";

    /**
     * An optional, string attribute. This text attribute identifies the endpoint that made the
     * conflicting modification. It is used and compared programmatically. See sx:update for format
     * guidance.
     * <p>
     * Note: Either or both of the when or by attributes MUST be present; it is invalid to have
     * neither.
     */
    public static final String BY_ATTRIBUTE = "by";

    /**
     * A required, integer attribute. This is the version number of the conflicting modification.
     */
    public static final String VERSION_ATTRIBUTE = "version";

    /**
     * An optional, date-time attribute. This is the date-time when the conflicting modification
     * took place. See sx:update for format guidance.
     * <p>
     * Note: Either or both of the when or by attributes MUST be present; it is invalid to have
     * neither.
     */
    public static final String WHEN_ATTRIBUTE = "when";

    private Integer version;
    private LocalDateTime when;
    private String by;
    private Item conflictItem;

    @Override
    public void copyFrom(final CopyFrom obj) {
        final Conflict conflict = (Conflict) obj;
        conflict.when = when == null ? null : when;
        conflict.by = by;
        conflict.version = version;
        try {
            conflict.conflictItem = (Item) conflictItem.clone();
        } catch (final CloneNotSupportedException e) {
            // should not happen
            LOG.error("Error", e);
        }
    }

    public String getBy() {
        return by;
    }

    public void setBy(final String by) {
        this.by = by;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(final LocalDateTime when) {
        this.when = when;
    }

    public void setItem(final Item conflictItem) {
        this.conflictItem = conflictItem;
    }

    public Item getItem() {
        return conflictItem;
    }
}
