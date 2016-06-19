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
 * The "event" object type represents an event that occurs in a certain place during a particular
 * interval of time.
 * </p>
 * <p>
 * The object type URL for the "event" object type is
 * <tt>http://activitystrea.ms/schema/1.0/event</tt>.
 * </p>
 * <p>
 * An event has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Start Date and Time</dt>
 * <dd>The date and time that the event begins. Represented in JSON as a property called
 * <tt>startTime</tt> whose value is JSON string containing a W3CDTF timestamp. [TODO: Reference
 * W3CDTF spec.] [TODO: Include prose describing how to represent just a date vs. a date and time.]</dd>
 * <dt>End Date and Time</dt>
 * <dd>The date and time that the event ends. Represented in JSON as a property called
 * <tt>endTime</tt> whose value is JSON string containing a W3CDTF timestamp. [TODO: Reference
 * W3CDTF spec.] [TODO: Include prose describing how to represent just a date vs. a date and time.]</dd>
 * </dl>
 * </blockquote>
 */
public class Event extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/event";
    }

}
