/*
 * This code is currently released under the Mozilla Public License.
 * http://www.mozilla.org/MPL/
 *
 * Alternately you may apply the terms of the Apache Software License
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.rometools.modules.mediarss.types;

import java.io.Serializable;

/**
 * Optional tag to specify the status of a media object -- whether it's still active or it has been blocked/deleted.
 * 
 * @since MediaRSS 1.5.0
 */
public class Status implements Serializable {
    private static final long serialVersionUID = 7136177408594285103L;

    /**
     * state can have values {@code active}, {@code blocked} or {@code deleted}.
     * 
     * {@code active} means a media object is active in the system, {@code blocked} means a media object is blocked by
     * the publisher, {@code deleted} means a media object has been deleted by the publisher.
     */
    public enum State {
        active, blocked, deleted;
    }

    private State state;
    private String reason;

    /**
     * state can have values {@code active}, {@code blocked} or {@code deleted}.
     * 
     * {@code active} means a media object is active in the system, {@code blocked} means a media object is blocked by
     * the publisher, {@code deleted} means a media object has been deleted by the publisher.
     * 
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * state can have values {@code active}, {@code blocked} or {@code deleted}.
     * 
     * {@code active} means a media object is active in the system, {@code blocked} means a media object is blocked by
     * the publisher, {@code deleted} means a media object has been deleted by the publisher.
     * 
     * @param state the state
     */
    public void setState(final State state) {
        this.state = state;
    }

    /**
     * reason is a reason explaining why a media object has been blocked/deleted. It can be plain text or a URL.
     * 
     * @return plain text or URL
     */
    public String getReason() {
        return reason;
    }

    /**
     * reason is a reason explaining why a media object has been blocked/deleted. It can be plain text or a URL.
     * 
     * @param reason plain text or URL
     */
    public void setReason(final String reason) {
        this.reason = reason;
    }

    // CHECKSTYLE:OFF
    @Override
    public String toString() {
        return "Status [state=" + state + ", reason=" + reason + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (reason == null ? 0 : reason.hashCode());
        result = prime * result + (state == null ? 0 : state.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Status other = (Status) obj;
        if (reason == null) {
            if (other.reason != null) {
                return false;
            }
        } else if (!reason.equals(other.reason)) {
            return false;
        }
        if (state != other.state) {
            return false;
        }
        return true;
    }
    // CHECKSTYLE:ON
}
