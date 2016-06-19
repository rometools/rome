/*
 *  Copyright 2011 robert.cooper.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.rometools.modules.activitystreams.types;

/**
 * <p>
 * The "status" Object type represents a human-readable update of the author's situation, mood,
 * location or other status.
 * </p>
 * <p>
 * A status is similar in structure to a note, but carries the additional meaning that the content
 * is primarily describing something its author is doing, feeling or experiencing.
 * </p>
 * <p>
 * A consumers MAY consider the content of the most recent status object it encountered to be the
 * user's current status, unless the most recent status update is old. When a status becomes too old
 * is not defined by this specification.
 * </p>
 * <p>
 * The "status" object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/status</tt>.
 * </p>
 * <p>
 * A status has the same components as a note.
 * </p>
 */
public class Status extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/status";
    }

}
