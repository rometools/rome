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
 * <sx:update>
 * </pre>
 *
 * Element within
 *
 * <pre>
 * <sx:history>
 * </pre>
 *
 * .
 */
public class Update extends SSEModule {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "update";
    public static final String BY_ATTRIBUTE = "by";
    public static final String WHEN_ATTRIBUTE = "when";

    private LocalDateTime when;
    private String by;

    @Override
    public void copyFrom(final CopyFrom other) {
        final Update otherUpdate = (Update) other;
        otherUpdate.when = when == null ? null : when;
        // dont copy immutable
        otherUpdate.by = by;
    }

    /**
     * Provides access to the date-time when the modification took place. If this attribute is
     * omitted the value defaults to the earliest time representable in RFC 822. Either or both of
     * the when or by attributes MUST be present; it is invalid to have neither.
     */
    public LocalDateTime getWhen() {
        return when;
    }

    /**
     * Set the date-time when the modification took place.
     *
     * @param when the date-time when the modification took place.
     */
    public void setWhen(final LocalDateTime when) {
        this.when = when;
    }

    /**
     * Provides access to a text attribute identifying the unique endpoint that made a modification.
     * This SHOULD be some combination of user and device (so that a given user can edit a feed on
     * multiple devices). This attribute is used programmatically to break ties in case two changes
     * happened at the same time (within the same second). Either or both of the when or by must be
     * present; it is invalid to have neither.
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
    public void setBy(final String by) {
        this.by = by;
    }
}
