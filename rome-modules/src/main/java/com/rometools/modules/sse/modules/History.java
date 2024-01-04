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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rometools.rome.feed.CopyFrom;

/**
 * <pre>
 * <sx:history>
 * </pre>
 *
 * Element within
 *
 * <pre>
 * <sx:sync>
 * </pre>
 *
 * .
 */
public class History extends SSEModule {
    private static final long serialVersionUID = 1L;

    // A date-time attribute.
    private LocalDateTime when;

    // A string attribute.
    private String by;

    public static final String NAME = "history";

    public static final String WHEN_ATTRIBUTE = "when";
    public static final String BY_ATTRIBUTE = "by";

    private List<Update> updates;

    public History() {
    }

    @Override
    public void copyFrom(final CopyFrom other) {
        final History otherHistory = (History) other;
        when = otherHistory.when == null ? null : (LocalDateTime) otherHistory.when;
        // dont copy immutable
        by = otherHistory.by;

        if (otherHistory.updates != null) {
            updates = new ArrayList<Update>();
            updates.addAll(otherHistory.updates);
        }
    }

    /**
     * Get the date-time when the most recent modification took place.
     * <p/>
     * This is the date-time when the most recent modification took place. If this attribute is
     * omitted the value defaults to the earliest time representable in RFC 822.
     *
     * @return the date-time when the most recent modification took place.
     */
    public LocalDateTime getWhen() {
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
    public void setWhen(final LocalDateTime when) {
        this.when = when;
    }

    /**
     * Provides access to a text attribute identifying the unique endpoint that made the most recent
     * modification. This SHOULD be some combination of user and device (so that a given user can
     * edit a feed on multiple devices). This attribute is used programmatically to break ties in
     * case two changes happened at the same time (within the same second).
     * <p/>
     * Either or both of the when or by must be present; it is invalid to have neither.
     * <p/>
     * If this attribute is omitted the value defaults to the empty string (which must be less than
     * all other values for purposes of collation).
     *
     * @return A text attribute identifying the unique endpoint that made the most recent
     *         modification.
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
    public void setBy(final String by) {
        this.by = by;
    }

    /**
     * Add an update to this history
     *
     * @param update an update to add to the list of updates for this history.
     */
    public void addUpdate(final Update update) {
        if (updates == null) {
            updates = new ArrayList<Update>();
        }
        updates.add(update);
    }

    /**
     * Return the list of updates for this history.
     *
     * @return the list of updates for this history.
     */
    public List<Update> getUpdates() {
        return updates;
    }
}
